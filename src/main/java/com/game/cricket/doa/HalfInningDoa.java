package com.game.cricket.doa;

import com.game.cricket.Match;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class HalfInningDoa {

    public void addHalfInningDetails(Match match){
        Connection conn = SingletonConnection.getInstance().getConn();
        String sql = "INSERT INTO `cricket`.`inning` VALUES (?,?,?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,match.getMatchId());
            pstmt.setBoolean(2,true);
            pstmt.setInt(3,Integer.parseInt(match.getTeam1().getTeamId()));
            pstmt.setInt(4,Integer.parseInt(match.getTeam2().getTeamId()));
            pstmt.execute();
            pstmt.setInt(1,match.getMatchId());
            pstmt.setBoolean(2,false);
            pstmt.setInt(3,Integer.parseInt(match.getTeam2().getTeamId()));
            pstmt.setInt(4,Integer.parseInt(match.getTeam1().getTeamId()));
            pstmt.execute();

        }
        catch (Exception e){
            System.out.println(e);
        }

    }
}
