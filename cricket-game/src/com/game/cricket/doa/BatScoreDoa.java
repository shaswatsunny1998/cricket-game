package com.game.cricket.doa;

import com.game.cricket.Match;
import com.game.cricket.models.Player;
import com.game.cricket.models.Team;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class BatScoreDoa {
    public void addBattingScores(Match match){
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cricket?autoReconnect=true&useSSL=false", "root", "12345678");

            Connection conn = SingletonConnection.getInstance().getConn();
            String sql = "INSERT INTO `cricket`.`battingscores` VALUES (?,?,?,?,?,?,?,?,?,?)";
            for(int i=0;i<match.getTeam1().getPlayers().size();++i){
                Player player = match.getTeam1().getPlayers().get(i);
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1,player.getPlayerId());
                pstmt.setInt(2,player.getScore().getDotRun());
                pstmt.setInt(3,player.getScore().getOneRun());
                pstmt.setInt(4,player.getScore().getTwoRun());
                pstmt.setInt(5,player.getScore().getThreeRun());
                pstmt.setInt(6,player.getScore().getFourRun());
                pstmt.setInt(7,player.getScore().getSixRun());
                pstmt.setInt(8,player.getScore().getTotalRun());
                pstmt.setBoolean(9,player.getScore().isOut());
                pstmt.setInt(10,player.getScore().getStrikeRate());
                pstmt.execute();
            }

            for(int i=0;i<match.getTeam2().getPlayers().size();++i){
                Player player = match.getTeam2().getPlayers().get(i);
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1,player.getPlayerId());
                pstmt.setInt(2,player.getScore().getDotRun());
                pstmt.setInt(3,player.getScore().getOneRun());
                pstmt.setInt(4,player.getScore().getTwoRun());
                pstmt.setInt(5,player.getScore().getThreeRun());
                pstmt.setInt(6,player.getScore().getFourRun());
                pstmt.setInt(7,player.getScore().getSixRun());
                pstmt.setInt(8,player.getScore().getTotalRun());
                pstmt.setBoolean(9,player.getScore().isOut());
                pstmt.setInt(10,player.getScore().getStrikeRate());
                pstmt.execute();
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
