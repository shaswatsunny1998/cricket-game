package com.game.cricket.doa;

import com.game.cricket.models.Team;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


@Repository
public class TeamDoa {

    //TODO Get Generated Key

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



    public Team getTeam(int teamId){
        Team team=null;
        try {
            Connection conn = SingletonConnection.getInstance().getConn();
            String sql = "SELECT * FROM cricket.team where teamid=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,teamId);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            team = new Team(rs.getString(1), rs.getString(2));
        } catch (Exception e) {
            System.out.println(e);
        }
        return team;
    }







    public List<Team> getTeams() {
        List<Team> teams = new ArrayList<Team>();
        try {
            Connection conn = SingletonConnection.getInstance().getConn();
            String sql = "SELECT * FROM cricket.team";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Team team = new Team(rs.getString(1), rs.getString(2));
                teams.add(team);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return teams;
    }

    public void deleteTeam(String teamId) {
        try {
            Connection conn = SingletonConnection.getInstance().getConn();
            String sql = "DELETE  from cricket.team where teamid=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(teamId));
            pstmt.execute();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void updateTeam(Team team,String teamId) {
        try {
            Connection conn = SingletonConnection.getInstance().getConn();
            String sql = "Update cricket.team set teamname = ? where teamid=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, team.getTeamName());
            pstmt.setInt(2, Integer.parseInt(teamId));
            pstmt.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
