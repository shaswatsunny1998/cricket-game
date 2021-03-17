package com.game.cricket.doa;

import com.game.cricket.models.Player;
import com.game.cricket.models.Team;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;


@Repository
public class TeamPlayerDoa {
    public void addTeamPlayer(int matchId, Team team) {
        try {
            Connection conn = SingletonConnection.getInstance().getConn();
            String sql = "INSERT INTO `cricket`.`teamplayers` VALUES (?,?,?)";
            for (int i = 0; i < team.getPlayers().size(); ++i) {
                Player player = team.getPlayers().get(i);
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, matchId);
                pstmt.setInt(2, player.getPlayerId());
                pstmt.setInt(3, Integer.parseInt(team.getTeamId()));
                pstmt.execute();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void addPlayer(int playerId,int teamId){
        try {
            Connection conn = SingletonConnection.getInstance().getConn();
            String sql = "INSERT INTO `cricket`.`teamplayers` VALUES (?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, playerId);
            pstmt.setInt(2, teamId);
            pstmt.execute();
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

}
