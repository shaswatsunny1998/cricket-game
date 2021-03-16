package com.game.cricket.doa;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ClearData {
    public void clearAllData() {
        try {
            Connection conn = SingletonConnection.getInstance().getConn();
            String[] arr = {"players", "teamplayers", "balls", "battingscores", "bowlingscores", "overs", "wickets", "scoreboard", "team","inning"};
            for (int i = 0; i < arr.length; ++i) {
                String sql = "Delete from " + arr[i];
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.execute();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void eraseData() {
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cricket?autoReconnect=true&useSSL=false", "root", "12345678");

            Connection conn = SingletonConnection.getInstance().getConn();
            String[] s = {"balls", "battingscores", "bowlingscores", "overs", "wickets", "scoreboard","inning"};
            String[] arr = {"players", "teamplayers","team"};
            for (int i = 0; i < s.length; ++i) {
                String sql = "Delete from " + s[i];
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.execute();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
