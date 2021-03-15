package com.game.cricket.doa;

import com.game.cricket.Match;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class TeamDoa {

    public void addTeams(Match match) {
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cricket?autoReconnect=true&useSSL=false", "root", "12345678");

            Connection conn = SingletonConnection.getInstance().getConn();
            String sql = "INSERT INTO `cricket`.`team` VALUES (?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(match.getTeam1().getTeamId()));
            pstmt.setString(2, match.getTeam1().getTeamName());
            pstmt.setInt(3, match.getTeam1().getTotal_score());
            pstmt.execute();

            pstmt.setInt(1, Integer.parseInt(match.getTeam2().getTeamId()));
            pstmt.setString(2, match.getTeam2().getTeamName());
            pstmt.setInt(3, match.getTeam2().getTotal_score());

            pstmt.execute();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
