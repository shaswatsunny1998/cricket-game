package com.game.cricket.doa;

import com.game.cricket.models.Batsman;
import com.game.cricket.models.Bowler;
import com.game.cricket.models.Player;
import com.game.cricket.models.Team;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


@Repository
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

    public Player getPlayer(int playerId) {
        Player player = null;
        try {
            Connection conn = SingletonConnection.getInstance().getConn();
            String sql = "SELECT * FROM cricket.players where playerid=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,playerId);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            if (rs.getString(5).equals("Batsman")) {
                player = new Batsman(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
            }
            if (rs.getString(5).equals("Bowler")) {
                player = new Bowler(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return player;
    }


    public List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<Player>();
        Player player = null;
        try {
            Connection conn = SingletonConnection.getInstance().getConn();
            String sql = "SELECT * FROM cricket.players";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                if (rs.getString(5).equals("Batsman")) {
                    player = new Batsman(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                }
                if (rs.getString(5).equals("Bowler")) {
                    player = new Bowler(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                }
                players.add(player);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return players;
    }

    public Player addPlayer(Player player){
        Player player1 = null;
        try {
            Connection conn = SingletonConnection.getInstance().getConn();
            String sql = "INSERT into cricket.players VALUES (?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, player.getPlayerId());
            pstmt.setString(2, player.getFirstName());
            pstmt.setString(3, player.getLastName());
            pstmt.setInt(4, player.getAge());
            pstmt.setString(5, player.getType());
            pstmt.execute();
            player1=player;
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return player1;
    }

    public List<Integer> getPlayers(int matchId, int teamId){
        List<Integer> playersId = new ArrayList<Integer>();
        try {
            Connection conn = SingletonConnection.getInstance().getConn();
            String sql = "SELECT * FROM cricket.players NATURAL JOIN cricket.teamplayers where matchid = ? and teamplayers.teamid = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,matchId);
            pstmt.setInt(2,teamId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                playersId.add(rs.getInt(1));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return playersId;
    }


    public List<Integer> getPlayersByMatch(int matchId)
    {
        List<Integer> playersId = new ArrayList<Integer>();
        try {
            Connection conn = SingletonConnection.getInstance().getConn();
            String sql = "SELECT * FROM cricket.players NATURAL JOIN cricket.teamplayers where matchid = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,matchId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                playersId.add(rs.getInt(1));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return playersId;
    }
}
