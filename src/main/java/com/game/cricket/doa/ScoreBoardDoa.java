package com.game.cricket.doa;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


@Repository
public class ScoreBoardDoa {
    public void addScoreBoard(int matchId, int winningTeam, int losingTeam, int winScore, int loseScore, int wicketWin, int wicketLose, boolean draw) {
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cricket?autoReconnect=true&useSSL=false", "root", "12345678");

            Connection conn = SingletonConnection.getInstance().getConn();
            String sql = "INSERT INTO `cricket`.`scoreboard` VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, matchId);
            pstmt.setInt(2, winningTeam);
            pstmt.setInt(3, losingTeam);
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

    public int getScore(int matchId, int teamId) {
        Connection conn = SingletonConnection.getInstance().getConn();
        String sql = "SELECT * FROM cricket.scoreboard where matchid=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, matchId);
            ResultSet rs  = pstmt.executeQuery();
            rs.next();
            if(rs.getInt(2)==teamId)
                return rs.getInt(4);
            else if(rs.getInt(3)==teamId)
                return rs.getInt(5);

        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
}
