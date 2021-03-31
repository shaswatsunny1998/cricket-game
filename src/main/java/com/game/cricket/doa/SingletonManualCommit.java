package com.game.cricket.doa;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;

@Repository
public class SingletonManualCommit {

    private static String dbUser;
    private static String dbPassword;

    private static SingletonManualCommit single_instance = null;
    private Connection conn;

    @Value("${db.user}")
    public void setUser(String user){
        SingletonManualCommit.dbUser =user;
    }

    @Value("${db.password}")
    public void setPassword(String password){
        SingletonManualCommit.dbPassword=password;
    }

    public Connection getConn() {
        return this.conn;
    }

    private SingletonManualCommit() {
        try {
            //TODO Without Component not working
            System.out.println(dbUser + " " + dbPassword);
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cricket?autoReconnect=true&useSSL=false", SingletonManualCommit.dbUser, SingletonManualCommit.dbPassword);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static SingletonManualCommit getInstance() {
        if (single_instance == null)
            single_instance = new SingletonManualCommit();
        return single_instance;
    }
}
