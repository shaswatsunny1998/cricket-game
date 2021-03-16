package com.game.cricket.doa;

import com.game.cricket.Match;
import com.game.cricket.models.Team;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class TeamDoa {

    public void addTeam(Team team) {
        try {
            Connection conn = SingletonConnection.getInstance().getConn();
            String sql = "INSERT INTO `cricket`.`team` VALUES (?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(team.getTeamId()));
            pstmt.setString(2, team.getTeamName());
            pstmt.execute();

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
