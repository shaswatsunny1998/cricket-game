package com.game.cricket.doa;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;

@Component
public class ClearData {

    public ClearData() {
    }

    public void clearAllData() {
        try {
            Connection conn = SingletonConnection.getInstance().getConn();
            String[] arr = {"players", "teamplayers", "balls", "battingscores", "bowlingscores", "overs", "wickets", "scoreboard", "team", "inning"};
            for (int i = 0; i < arr.length; ++i) {
                String sql = "Delete from " + arr[i];
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.execute();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void eraseData() {
        try {
            Connection conn = SingletonConnection.getInstance().getConn();
            String[] s = {"balls", "battingscores", "bowlingscores", "overs", "wickets", "scoreboard", "inning","teamplayers"};
            String[] arr = {"players", "team"};
            for (int i = 0; i < s.length; ++i) {
                String sql = "Delete from " + s[i];
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.execute();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
