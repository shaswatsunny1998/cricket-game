package com.game.cricket.doa;

import com.game.cricket.Match;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;


@Repository
public class HalfInningDoa {

    public void addHalfInningDetails(Match match) {
        Connection conn = SingletonConnection.getInstance().getConn();
        String sql = "INSERT INTO `cricket`.`inning` VALUES (?,?,?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, match.getMatchId());
            pstmt.setBoolean(2, true);
            pstmt.setInt(3, Integer.parseInt(match.getTeam1().getTeamId()));
            pstmt.setInt(4, Integer.parseInt(match.getTeam2().getTeamId()));
            pstmt.execute();
            pstmt.setInt(1, match.getMatchId());
            pstmt.setBoolean(2, false);
            pstmt.setInt(3, Integer.parseInt(match.getTeam2().getTeamId()));
            pstmt.setInt(4, Integer.parseInt(match.getTeam1().getTeamId()));
            pstmt.execute();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public Map getInning(int matchId) {
        Map map = new HashMap<>();
        Connection conn = SingletonConnection.getInstance().getConn();
        String sql = "SELECT * FROM cricket.inning where matchid =? order by firsthalf desc";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, matchId);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            map.put("battingTeam",rs.getInt(3));
            map.put("bowlingTeam",rs.getInt(4));
        } catch (Exception e) {
            System.out.println(e);
        }
        return map;
    }
}
