package com.game.cricket.doa;

import com.game.cricket.models.Player;
import com.game.cricket.models.Team;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class PlayersDoa {
    public void addPlayers(Team team1, Team team2) {
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cricket?autoReconnect=true&useSSL=false", "root", "12345678");

            Connection conn = SingletonConnection.getInstance().getConn();
            String sql = "INSERT INTO `cricket`.`players` VALUES (?,?,?,?,?)";
            for (int i = 0; i < team1.getPlayers().size(); ++i) {
                Player player = team1.getPlayers().get(i);
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, player.getPlayerId());
                pstmt.setString(2, player.getFirstName());
                pstmt.setString(3, player.getLastName());
                pstmt.setInt(4, player.getAge());
                pstmt.setString(5, player.getType());
                pstmt.execute();
            }

            for (int i = 0; i < team2.getPlayers().size(); ++i) {
                Player player = team2.getPlayers().get(i);
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, player.getPlayerId());
                pstmt.setString(2, player.getFirstName());
                pstmt.setString(3, player.getLastName());
                pstmt.setInt(4, player.getAge());
                pstmt.setString(5, player.getType());
                pstmt.execute();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
