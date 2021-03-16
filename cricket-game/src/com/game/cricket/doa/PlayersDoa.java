package com.game.cricket.doa;

import com.game.cricket.models.Player;
import com.game.cricket.models.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PlayersDoa {
    public void addPlayers(Team team) {
        try {
            Connection conn = SingletonConnection.getInstance().getConn();
            String sql = "INSERT INTO `cricket`.`players` VALUES (?,?,?,?,?)";
            for (int i = 0; i < team.getPlayers().size(); ++i) {
                Player player = team.getPlayers().get(i);
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
