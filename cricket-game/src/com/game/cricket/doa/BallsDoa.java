package com.game.cricket.doa;

import com.game.cricket.Match;
import com.game.cricket.models.Ball;
import com.game.cricket.models.Over;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class BallsDoa {
    public void addBalls(Match match) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cricket?autoReconnect=true&useSSL=false", "root", "12345678");
            String sql = "INSERT INTO `cricket`.`balls` VALUES (?,?,?,?,?,?)";
            for (int i = 0; i < match.getFirstHalfOvers().size(); ++i) {
                Over over = match.getFirstHalfOvers().get(i);
                for (int j = 0; j < over.getBalls().size(); ++j) {
                    Ball ball = over.getBalls().get(j);
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, (i + 1));
                    pstmt.setBoolean(2, true);
                    pstmt.setInt(3, (j + 1));
                    pstmt.setInt(4, ball.getRun());
                    pstmt.setInt(5, ball.getBatsmanId());
                    pstmt.setInt(6, ball.getBowlerId());
                    pstmt.execute();
                }
            }
            for (int i = 0; i < match.getSecondHalfOvers().size(); ++i) {
                Over over = match.getSecondHalfOvers().get(i);
                for (int j = 0; j < over.getBalls().size(); ++j) {
                    Ball ball = over.getBalls().get(j);
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, (i + 1));
                    pstmt.setBoolean(2, false);
                    pstmt.setInt(3, (j + 1));
                    pstmt.setInt(4, ball.getRun());
                    pstmt.setInt(5, ball.getBatsmanId());
                    pstmt.setInt(6, ball.getBowlerId());
                    pstmt.execute();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
