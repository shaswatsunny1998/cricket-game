package com.game.cricket.doa;

import com.game.cricket.Match;
import com.game.cricket.models.Over;
import com.game.cricket.models.Wicket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class WicketDoa {
    public void addWickets(Match match) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cricket?autoReconnect=true&useSSL=false", "root", "12345678");
            String sql = "INSERT INTO `cricket`.`wickets` VALUES (?,?,?,?,?,?)";
            for (int i = 0; i < match.getFirstHalfOvers().size(); ++i) {
                Over over = match.getFirstHalfOvers().get(i);
                for (int j = 0; j < over.getWickets().size(); ++j) {
                    Wicket wicket = over.getWickets().get(j);
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, (i + 1));
                    pstmt.setBoolean(2, true);
                    pstmt.setInt(3, wicket.getBallNo());
                    pstmt.setString(4, wicket.getBatsman());
                    pstmt.setString(5, wicket.getBowler());
                    pstmt.setString(6, wicket.getType());
                    pstmt.execute();
                }
            }

            for (int i = 0; i < match.getSecondHalfOvers().size(); ++i) {
                Over over = match.getSecondHalfOvers().get(i);
                for (int j = 0; j < over.getWickets().size(); ++j) {
                    Wicket wicket = over.getWickets().get(j);
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, (i + 1));
                    pstmt.setBoolean(2, false);
                    pstmt.setInt(3, wicket.getBallNo());
                    pstmt.setString(4, wicket.getBatsman());
                    pstmt.setString(5, wicket.getBowler());
                    pstmt.setString(6, wicket.getType());
                    pstmt.execute();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
