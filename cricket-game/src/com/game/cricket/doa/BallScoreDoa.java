package com.game.cricket.doa;

import com.game.cricket.Match;
import com.game.cricket.models.Player;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class BallScoreDoa {
    public void addBowlingScores(Match match){
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cricket?autoReconnect=true&useSSL=false", "root", "12345678");
            Connection conn = SingletonConnection.getInstance().getConn();
            String sql = "INSERT INTO `cricket`.`bowlingscores` VALUES (?,?,?,?,?,?,?,?,?,?)";
            for(int i=0;i<match.getTeam1().getPlayers().size();++i){
                Player player = match.getTeam1().getPlayers().get(i);
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, Integer.parseInt(match.getMatchId()));
                pstmt.setInt(2,player.getPlayerId());
                pstmt.setInt(3,player.getScore().getWickets());
                pstmt.setInt(4,player.getScore().getDotBalls());
                pstmt.setInt(5,player.getScore().getOneRunBalls());
                pstmt.setInt(6,player.getScore().getTwoRunBalls());
                pstmt.setInt(7,player.getScore().getThreeRunBalls());
                pstmt.setInt(8,player.getScore().getFourRunBalls());
                pstmt.setInt(9,player.getScore().getSixRunBalls());
                pstmt.setInt(10,player.getScore().getCurrBall());
                pstmt.execute();
            }

            for(int i=0;i<match.getTeam2().getPlayers().size();++i){
                Player player = match.getTeam2().getPlayers().get(i);
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, Integer.parseInt(match.getMatchId()));
                pstmt.setInt(2,player.getPlayerId());
                pstmt.setInt(3,player.getScore().getWickets());
                pstmt.setInt(4,player.getScore().getDotBalls());
                pstmt.setInt(5,player.getScore().getOneRunBalls());
                pstmt.setInt(6,player.getScore().getTwoRunBalls());
                pstmt.setInt(7,player.getScore().getThreeRunBalls());
                pstmt.setInt(8,player.getScore().getFourRunBalls());
                pstmt.setInt(9,player.getScore().getSixRunBalls());
                pstmt.setInt(10,player.getScore().getCurrBall());
                pstmt.execute();
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
