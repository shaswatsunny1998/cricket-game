package com.game.cricket.doa;

import com.game.cricket.Match;
import com.game.cricket.models.Over;
import com.game.cricket.models.Wicket;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;


@Repository
public class WicketDoa {
    public void addWickets(Match match) {
        try {
            Connection conn = SingletonConnection.getInstance().getConn();
            String sql = "INSERT INTO `cricket`.`wickets` VALUES (?,?,?,?,?,?,?)";
            for (int i = 0; i < match.getFirstHalfOvers().size(); ++i) {
                Over over = match.getFirstHalfOvers().get(i);
                for (int j = 0; j < over.getWickets().size(); ++j) {
                    Wicket wicket = over.getWickets().get(j);
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, match.getMatchId());
                    pstmt.setInt(2, (i + 1));
                    pstmt.setBoolean(3, true);
                    pstmt.setInt(4, wicket.getBallNo());
                    pstmt.setInt(5, wicket.getBatsman());
                    pstmt.setInt(6, wicket.getBowler());
                    pstmt.setString(7, wicket.getType());
                    pstmt.execute();
                }
            }

            for (int i = 0; i < match.getSecondHalfOvers().size(); ++i) {
                Over over = match.getSecondHalfOvers().get(i);
                for (int j = 0; j < over.getWickets().size(); ++j) {
                    Wicket wicket = over.getWickets().get(j);
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, match.getMatchId());
                    pstmt.setInt(2, (i + 1));
                    pstmt.setBoolean(3, false);
                    pstmt.setInt(4, wicket.getBallNo());
                    pstmt.setInt(5, wicket.getBatsman());
                    pstmt.setInt(6, wicket.getBowler());
                    pstmt.setString(7, wicket.getType());
                    pstmt.execute();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }


    public void addWicket(int matchId, int overNo, boolean firstHalf, int ballNo, int batsmanId, int bowlerId) {
        Connection conn = SingletonConnection.getInstance().getConn();
        try {

            String sql = "INSERT INTO `cricket`.`wickets` VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, matchId);
            pstmt.setInt(2, overNo);
            pstmt.setBoolean(3, firstHalf);
            pstmt.setInt(4, ballNo);
            pstmt.setInt(5, batsmanId);
            pstmt.setInt(6, bowlerId);
            pstmt.setString(7, "Bowled");
            pstmt.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
