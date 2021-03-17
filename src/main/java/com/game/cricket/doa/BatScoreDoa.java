package com.game.cricket.doa;

import com.game.cricket.Match;
import com.game.cricket.models.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BatScoreDoa {
    public void addBattingScores(Match match) {
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cricket?autoReconnect=true&useSSL=false", "root", "12345678");

            Connection conn = SingletonConnection.getInstance().getConn();
            String sql = "INSERT INTO `cricket`.`battingscores` VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            for (int i = 0; i < match.getTeam1().getPlayers().size(); ++i) {
                Player player = match.getTeam1().getPlayers().get(i);
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, match.getMatchId());
                pstmt.setInt(2, player.getPlayerId());
                pstmt.setInt(3, player.getScore().getDotRun());
                pstmt.setInt(4, player.getScore().getOneRun());
                pstmt.setInt(5, player.getScore().getTwoRun());
                pstmt.setInt(6, player.getScore().getThreeRun());
                pstmt.setInt(7, player.getScore().getFourRun());
                pstmt.setInt(8, player.getScore().getSixRun());
                pstmt.setInt(9, player.getScore().getTotalRun());
                pstmt.setBoolean(10, player.getScore().isOut());
                pstmt.setDouble(11, player.getScore().getTotalStrikeRate());
                pstmt.execute();
            }

            for (int i = 0; i < match.getTeam2().getPlayers().size(); ++i) {
                Player player = match.getTeam2().getPlayers().get(i);
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, match.getMatchId());
                pstmt.setInt(2, player.getPlayerId());
                pstmt.setInt(3, player.getScore().getDotRun());
                pstmt.setInt(4, player.getScore().getOneRun());
                pstmt.setInt(5, player.getScore().getTwoRun());
                pstmt.setInt(6, player.getScore().getThreeRun());
                pstmt.setInt(7, player.getScore().getFourRun());
                pstmt.setInt(8, player.getScore().getSixRun());
                pstmt.setInt(9, player.getScore().getTotalRun());
                pstmt.setBoolean(10, player.getScore().isOut());
                pstmt.setDouble(11, player.getScore().getTotalStrikeRate());
                pstmt.execute();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
