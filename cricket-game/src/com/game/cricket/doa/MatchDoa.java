package com.game.cricket.doa;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class MatchDoa {

    public void addMatch(String matchId, String matchName, String firstTeamId, String secondTeamId) {
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cricket?autoReconnect=true&useSSL=false", "root", "12345678");
            Connection conn = SingletonConnection.getInstance().getConn();
            String sql = "INSERT INTO `cricket`.`match` VALUES (?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(matchId));
            pstmt.setString(2, matchName);
            pstmt.setInt(3, Integer.parseInt(firstTeamId));
            pstmt.setInt(4, Integer.parseInt(secondTeamId));
            pstmt.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
