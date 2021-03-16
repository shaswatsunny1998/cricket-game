package com.game.cricket.doa;

import com.game.cricket.Match;
import com.game.cricket.models.Over;
import com.game.cricket.models.Wicket;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class WicketDoa {
    public void addWickets(Match match) {
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cricket?autoReconnect=true&useSSL=false", "root", "12345678");
            Connection conn = SingletonConnection.getInstance().getConn();
            String sql = "INSERT INTO `cricket`.`wickets` VALUES (?,?,?,?,?,?,?)";
            for (int i = 0; i < match.getFirstHalfOvers().size(); ++i) {
                Over over = match.getFirstHalfOvers().get(i);
                for (int j = 0; j < over.getWickets().size(); ++j) {
                    Wicket wicket = over.getWickets().get(j);
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, Integer.parseInt(match.getMatchId()));
                    pstmt.setInt(2, (i + 1));
                    pstmt.setBoolean(3, true);
                    pstmt.setInt(4, wicket.getBallNo());
                    pstmt.setInt(5, wicket.getBatsman());
                    pstmt.setInt(6, wicket.getBowler());
                    pstmt.setString(7, wicket.getType());
                    pstmt.execute();
                }
            }

            for (int i = 0; i < match.getSecondHalfOvers().size(); ++i) {
                Over over = match.getSecondHalfOvers().get(i);
                for (int j = 0; j < over.getWickets().size(); ++j) {
                    Wicket wicket = over.getWickets().get(j);
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, Integer.parseInt(match.getMatchId()));
                    pstmt.setInt(2, (i + 1));
                    pstmt.setBoolean(3, false);
                    pstmt.setInt(4, wicket.getBallNo());
                    pstmt.setInt(5, wicket.getBatsman());
                    pstmt.setInt(6, wicket.getBowler());
                    pstmt.setString(7, wicket.getType());
                    pstmt.execute();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
