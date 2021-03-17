package com.game.cricket.doa;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ScoreBoardDoa {
    public void addScoreBoard(int matchId, String winningTeam, String losingTeam, int winScore, int loseScore, int wicketWin, int wicketLose, boolean draw) {
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cricket?autoReconnect=true&useSSL=false", "root", "12345678");

            Connection conn = SingletonConnection.getInstance().getConn();
            String sql = "INSERT INTO `cricket`.`scoreboard` VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, matchId);
            pstmt.setString(2, winningTeam);
            pstmt.setString(3, losingTeam);
            pstmt.setInt(4, winScore);
            pstmt.setInt(5, loseScore);
            pstmt.setInt(6, wicketWin);
            pstmt.setInt(7, wicketLose);
            pstmt.setBoolean(8, draw);
            pstmt.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
