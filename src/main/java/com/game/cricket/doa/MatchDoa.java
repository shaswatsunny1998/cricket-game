package com.game.cricket.doa;

import com.game.cricket.Match;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


@Repository
public class MatchDoa {

    public void addMatch(Match match) {
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cricket?autoReconnect=true&useSSL=false", "root", "12345678");
            Connection conn = SingletonConnection.getInstance().getConn();
            String sql = "INSERT INTO `cricket`.`match` VALUES (?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, match.getMatchId());
            pstmt.setString(2, match.getMatchName());
            pstmt.setString(3, match.getVenue());
            pstmt.setInt(4, Integer.parseInt(match.getTeam1().getTeamId()));
            pstmt.setInt(5, Integer.parseInt(match.getTeam2().getTeamId()));
            pstmt.setDate(6, new Date(match.getMatchDate().getTime()));
            pstmt.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public Match getMatch(int matchId){
        Match match =null;
        try{
            Connection conn = SingletonConnection.getInstance().getConn();
            String sql = "Select * from `cricket`.`match` where matchid=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, matchId);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            match = new Match(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(6));
        }
        catch (Exception e){
            System.out.println(e);
        }
        return match;
    }
}
