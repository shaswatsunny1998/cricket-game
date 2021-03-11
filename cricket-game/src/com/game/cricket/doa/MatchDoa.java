package com.game.cricket.doa;

import java.sql.*;

public class MatchDoa {

    public void addMatch(String matchId,String matchName){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cricket?autoReconnect=true&useSSL=false", "root", "12345678");
            String sql = "INSERT INTO `cricket`.`match` (`match_id`, `matchname`) VALUES (?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,Integer.parseInt(matchId));
            pstmt.setString(2,matchName);
            pstmt.execute();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
