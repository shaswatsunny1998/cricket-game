package com.game.cricket.doa;

import com.game.cricket.Match;
import com.game.cricket.models.Player;
import com.game.cricket.models.Score;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


@Repository
public class BallScoreDoa {
    public void addBowlingScores(Match match){
        try {
            Connection conn = SingletonConnection.getInstance().getConn();
            String sql = "INSERT INTO `cricket`.`bowlingscores` VALUES (?,?,?,?,?,?,?,?,?,?)";
            for(int i=0;i<match.getTeam1().getPlayers().size();++i){
                Player player = match.getTeam1().getPlayers().get(i);
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, match.getMatchId());
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
                pstmt.setInt(1, match.getMatchId());
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

    public void addBowlingScore(int matchId, Player player){
        try {
            Connection conn = SingletonConnection.getInstance().getConn();
            String sql = "REPLACE INTO `cricket`.`bowlingscores` VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, matchId);
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
        catch (Exception e)
        {
            System.out.println(e);
        }
    }


    public Score getBowlingScore(int matchId,int playerId){
        Score score=new Score();
        try{
            Connection conn = SingletonConnection.getInstance().getConn();
            String sql = "Select * from `cricket`.`bowlingscores` where matchid=? and playerid=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,matchId);
            pstmt.setInt(2,playerId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                score = new Score(rs.getInt(3),rs.getInt(4),rs.getInt(5)
                ,rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getInt(9)
                ,rs.getInt(10));
            }

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return score;
    }



}
