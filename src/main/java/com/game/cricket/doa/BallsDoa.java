package com.game.cricket.doa;

import com.game.cricket.Match;
import com.game.cricket.models.Ball;
import com.game.cricket.models.Over;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BallsDoa {
    public void addBalls(Match match) {
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cricket?autoReconnect=true&useSSL=false", "root", "12345678");
            Connection conn = SingletonConnection.getInstance().getConn();
            String sql = "INSERT INTO `cricket`.`balls` VALUES (?,?,?,?,?,?,?)";
            for (int i = 0; i < match.getFirstHalfOvers().size(); ++i) {
                Over over = match.getFirstHalfOvers().get(i);
                for (int j = 0; j < over.getBalls().size(); ++j) {
                    Ball ball = over.getBalls().get(j);
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, match.getMatchId());
                    pstmt.setInt(2, (i + 1));
                    pstmt.setBoolean(3, true);
                    pstmt.setInt(4, (j + 1));
                    pstmt.setInt(5, ball.getRun());
                    pstmt.setInt(6, ball.getBatsmanId());
                    pstmt.setInt(7, ball.getBowlerId());
                    pstmt.execute();
                }
            }
            for (int i = 0; i < match.getSecondHalfOvers().size(); ++i) {
                Over over = match.getSecondHalfOvers().get(i);
                for (int j = 0; j < over.getBalls().size(); ++j) {
                    Ball ball = over.getBalls().get(j);
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, match.getMatchId());
                    pstmt.setInt(2, (i + 1));
                    pstmt.setBoolean(3, false);
                    pstmt.setInt(4, (j + 1));
                    pstmt.setInt(5, ball.getRun());
                    pstmt.setInt(6, ball.getBatsmanId());
                    pstmt.setInt(7, ball.getBowlerId());
                    pstmt.execute();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void addBall(int matchId, int overNo, boolean firstHalf, int ballNo, int run, int batsmanId, int bowlerId) {
        Connection conn = SingletonConnection.getInstance().getConn();
        try {

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
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public List<Ball> getBalls(int matchId,boolean firstHalf) {
        List<Ball> balls = new ArrayList<>();
        Connection conn = SingletonConnection.getInstance().getConn();
        try {
            String sql = "SELECT * FROM cricket.balls where matchid=? and firsthalf = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, matchId);
            pstmt.setBoolean(2, firstHalf);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                balls.add(new Ball(rs.getInt(5), rs.getInt(6), rs.getInt(7)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return balls;
    }


    public List<Ball> getBallsPlayer(int matchId,int playerId ,boolean batsman)
    {
        List<Ball> balls = new ArrayList<>();
        Connection conn = SingletonConnection.getInstance().getConn();
        String sql="";
        try {
            if(batsman)
            {
                sql = "SELECT * FROM cricket.balls where matchid=? and batsmanid =?";
            }
            else
            {
                sql = "SELECT * FROM cricket.balls where matchid=? and bowlerid=?";
            }
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,matchId);
            pstmt.setInt(2,playerId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                balls.add(new Ball(rs.getInt(5), rs.getInt(6), rs.getInt(7)));
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return balls;
    }


}
