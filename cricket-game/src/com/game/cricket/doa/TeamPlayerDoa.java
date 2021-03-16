package com.game.cricket.doa;

import com.game.cricket.models.Player;
import com.game.cricket.models.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TeamPlayerDoa {
    public void addTeamPlayer(Team team) {
        try {
            Connection conn = SingletonConnection.getInstance().getConn();
            String sql = "INSERT INTO `cricket`.`teamplayers` VALUES (?,?)";
            for (int i = 0; i < team.getPlayers().size(); ++i) {
                Player player = team.getPlayers().get(i);
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, player.getPlayerId());
                pstmt.setInt(2, Integer.parseInt(team.getTeamId()));
                pstmt.execute();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
