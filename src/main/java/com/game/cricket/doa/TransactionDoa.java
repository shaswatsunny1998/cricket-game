package com.game.cricket.doa;

import com.game.cricket.models.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionDoa {
    public void addWithoutWicket(int matchId, int overNo, boolean firstHalf, int ballNo, int run, int batsmanId, int bowlerId, Player batsman, Player bowler) {
        Connection conn = SingletonManualCommit.getInstance().getConn();
        try {
            conn.setAutoCommit(false);

            String sql = "INSERT INTO `cricket`.`balls` VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, matchId);
            pstmt.setInt(2, overNo);
            pstmt.setBoolean(3, firstHalf);
            pstmt.setInt(4, ballNo);
            pstmt.setInt(5, run);
            pstmt.setInt(6, batsmanId);
            pstmt.setInt(7, bowlerId);
            pstmt.execute();

            String sql1 = "REPLACE INTO `cricket`.`bowlingscores` VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt1 = conn.prepareStatement(sql1);
            pstmt1.setInt(1, matchId);
            pstmt1.setInt(2, bowler.getPlayerId());
            //Even with rollback , scores are still updated in memory.
            pstmt1.setInt(3, bowler.getScore().getWickets());
            pstmt1.setInt(4, bowler.getScore().getDotBalls());
            pstmt1.setInt(5, bowler.getScore().getOneRunBalls());
            pstmt1.setInt(6, bowler.getScore().getTwoRunBalls());
            pstmt1.setInt(7, bowler.getScore().getThreeRunBalls());
            pstmt1.setInt(8, bowler.getScore().getFourRunBalls());
            pstmt1.setInt(9, bowler.getScore().getSixRunBalls());
            pstmt1.setInt(10, bowler.getScore().getCurrBall());
            pstmt1.execute();

            String sql2 = "REPLACE INTO `cricket`.`battingscores` VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setInt(1, matchId);
            pstmt2.setInt(2, batsman.getPlayerId());
            pstmt2.setInt(3, batsman.getScore().getDotRun());
            pstmt2.setInt(4, batsman.getScore().getOneRun());
            pstmt2.setInt(5, batsman.getScore().getTwoRun());
            pstmt2.setInt(6, batsman.getScore().getThreeRun());
            pstmt2.setInt(7, batsman.getScore().getFourRun());
            pstmt2.setInt(8, batsman.getScore().getSixRun());
            pstmt2.setInt(9, batsman.getScore().getTotalRun());
            pstmt2.setBoolean(10, batsman.getScore().isOut());
            pstmt2.setDouble(11, batsman.getScore().getTotalStrikeRate());
            pstmt2.execute();

//            if(batsman.getPlayerId()==0)
//            {
//                conn.rollback();
//                System.out.println("Roll Back Happened");
//            }
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            System.out.println("Roll Back Happened");
            System.out.println(e);
        }

    }

    public void addWithWicket(int matchId, int overNo, boolean firstHalf, int ballNo, int run, int batsmanId, int bowlerId, Player batsman, Player bowler) {
        Connection conn = SingletonManualCommit.getInstance().getConn();
        try {
            conn.setAutoCommit(false);

            String sql = "INSERT INTO `cricket`.`balls` VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, matchId);
            pstmt.setInt(2, overNo);
            pstmt.setBoolean(3, firstHalf);
            pstmt.setInt(4, ballNo);
            pstmt.setInt(5, run);
            pstmt.setInt(6, batsmanId);
            pstmt.setInt(7, bowlerId);
            pstmt.execute();

            String sql1 = "REPLACE INTO `cricket`.`bowlingscores` VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt1 = conn.prepareStatement(sql1);
            pstmt1.setInt(1, matchId);
            pstmt1.setInt(2, bowler.getPlayerId());
            //Even with rollback , scores are still updated in memory.
            pstmt1.setInt(3, bowler.getScore().getWickets());
            pstmt1.setInt(4, bowler.getScore().getDotBalls());
            pstmt1.setInt(5, bowler.getScore().getOneRunBalls());
            pstmt1.setInt(6, bowler.getScore().getTwoRunBalls());
            pstmt1.setInt(7, bowler.getScore().getThreeRunBalls());
            pstmt1.setInt(8, bowler.getScore().getFourRunBalls());
            pstmt1.setInt(9, bowler.getScore().getSixRunBalls());
            pstmt1.setInt(10, bowler.getScore().getCurrBall());
            pstmt1.execute();

            String sql2 = "REPLACE INTO `cricket`.`battingscores` VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setInt(1, matchId);
            pstmt2.setInt(2, batsman.getPlayerId());
            pstmt2.setInt(3, batsman.getScore().getDotRun());
            pstmt2.setInt(4, batsman.getScore().getOneRun());
            pstmt2.setInt(5, batsman.getScore().getTwoRun());
            pstmt2.setInt(6, batsman.getScore().getThreeRun());
            pstmt2.setInt(7, batsman.getScore().getFourRun());
            pstmt2.setInt(8, batsman.getScore().getSixRun());
            pstmt2.setInt(9, batsman.getScore().getTotalRun());
            pstmt2.setBoolean(10, batsman.getScore().isOut());
            pstmt2.setDouble(11, batsman.getScore().getTotalStrikeRate());
            pstmt2.execute();


            String sql3 = "INSERT INTO `cricket`.`wickets` VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pstmt3 = conn.prepareStatement(sql3);
            pstmt3.setInt(1, matchId);
            pstmt3.setInt(2, overNo);
            pstmt3.setBoolean(3, firstHalf);
            pstmt3.setInt(4, ballNo);
            pstmt3.setInt(5, batsmanId);
            pstmt3.setInt(6, bowlerId);
            pstmt3.setString(7, "Bowled");
            pstmt3.execute();


            conn.commit();

        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            System.out.println("Roll Back Happened");
            System.out.println(e);
        }

    }

}

