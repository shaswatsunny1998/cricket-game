package com.game.cricket.doa;

import com.game.cricket.Match;
import com.game.cricket.models.Over;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class OversDoa {
    public void addOvers(Match match) {
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cricket?autoReconnect=true&useSSL=false", "root", "12345678");
            Connection conn = SingletonConnection.getInstance().getConn();
            String sql = "INSERT INTO `cricket`.`overs` VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            for (int i = 0; i < match.getFirstHalfOvers().size(); ++i) {
                Over over = match.getFirstHalfOvers().get(i);
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, (i + 1));
                pstmt.setBoolean(2, true);
                pstmt.setInt(3, over.getPlayerId());
                pstmt.setInt(4, over.getWicket());
                pstmt.setInt(5, over.getDotBalls());
                pstmt.setInt(6, over.getOneRun());
                pstmt.setInt(7, over.getTwoRun());
                pstmt.setInt(8, over.getThreeRun());
                pstmt.setInt(9, over.getFourRun());
                pstmt.setInt(10, over.getSixRun());
                pstmt.setInt(11, over.getTotalRun());
                pstmt.setInt(12, over.getCurrBall());
                pstmt.execute();
            }

            for (int i = 0; i < match.getSecondHalfOvers().size(); ++i) {
                Over over = match.getSecondHalfOvers().get(i);
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, (i + 1));
                pstmt.setBoolean(2, false);
                pstmt.setInt(3, over.getPlayerId());
                pstmt.setInt(4, over.getWicket());
                pstmt.setInt(5, over.getDotBalls());
                pstmt.setInt(6, over.getOneRun());
                pstmt.setInt(7, over.getTwoRun());
                pstmt.setInt(8, over.getThreeRun());
                pstmt.setInt(9, over.getFourRun());
                pstmt.setInt(10, over.getSixRun());
                pstmt.setInt(11, over.getTotalRun());
                pstmt.setInt(12, over.getCurrBall());
                pstmt.execute();
            }


        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
