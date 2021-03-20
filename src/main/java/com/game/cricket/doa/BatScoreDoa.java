package com.game.cricket.doa;

import com.game.cricket.Match;
import com.game.cricket.models.Player;
import com.game.cricket.models.Score;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


@Repository
public class BatScoreDoa {
    public void addBattingScores(Match match) {
        try {
            Connection conn = SingletonConnection.getInstance().getConn();
            String sql = "INSERT INTO `cricket`.`battingscores` VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            for (int i = 0; i < match.getTeam1().getPlayers().size(); ++i) {
                Player player = match.getTeam1().getPlayers().get(i);
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, match.getMatchId());
                pstmt.setInt(2, player.getPlayerId());
                pstmt.setInt(3, player.getScore().getDotRun());
                pstmt.setInt(4, player.getScore().getOneRun());
                pstmt.setInt(5, player.getScore().getTwoRun());
                pstmt.setInt(6, player.getScore().getThreeRun());
                pstmt.setInt(7, player.getScore().getFourRun());
                pstmt.setInt(8, player.getScore().getSixRun());
                pstmt.setInt(9, player.getScore().getTotalRun());
                pstmt.setBoolean(10, player.getScore().isOut());
                pstmt.setDouble(11, player.getScore().getTotalStrikeRate());
                pstmt.execute();
            }

            for (int i = 0; i < match.getTeam2().getPlayers().size(); ++i) {
                Player player = match.getTeam2().getPlayers().get(i);
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, match.getMatchId());
                pstmt.setInt(2, player.getPlayerId());
                pstmt.setInt(3, player.getScore().getDotRun());
                pstmt.setInt(4, player.getScore().getOneRun());
                pstmt.setInt(5, player.getScore().getTwoRun());
                pstmt.setInt(6, player.getScore().getThreeRun());
                pstmt.setInt(7, player.getScore().getFourRun());
                pstmt.setInt(8, player.getScore().getSixRun());
                pstmt.setInt(9, player.getScore().getTotalRun());
                pstmt.setBoolean(10, player.getScore().isOut());
                pstmt.setDouble(11, player.getScore().getTotalStrikeRate());
                pstmt.execute();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void addBattingScore(int matchId,Player player){
        Connection conn = SingletonConnection.getInstance().getConn();
        String sql = "REPLACE INTO `cricket`.`battingscores` VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, matchId);
            pstmt.setInt(2, player.getPlayerId());
            pstmt.setInt(3, player.getScore().getDotRun());
            pstmt.setInt(4, player.getScore().getOneRun());
            pstmt.setInt(5, player.getScore().getTwoRun());
            pstmt.setInt(6, player.getScore().getThreeRun());
            pstmt.setInt(7, player.getScore().getFourRun());
            pstmt.setInt(8, player.getScore().getSixRun());
            pstmt.setInt(9, player.getScore().getTotalRun());
            pstmt.setBoolean(10, player.getScore().isOut());
            pstmt.setDouble(11, player.getScore().getTotalStrikeRate());
            pstmt.execute();
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    public Score getBattingScore(int matchId, int playerId){
        Score score=new Score();
        try{
            Connection conn = SingletonConnection.getInstance().getConn();
            String sql = "Select * from `cricket`.`battingscores` where matchid=? and playerid=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,matchId);
            pstmt.setInt(2,playerId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                score = new Score(rs.getInt(3),rs.getInt(4),rs.getInt(5)
                        ,rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getInt(9)
                        ,rs.getBoolean(10),rs.getInt(11));
            }

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return score;
    }



}
