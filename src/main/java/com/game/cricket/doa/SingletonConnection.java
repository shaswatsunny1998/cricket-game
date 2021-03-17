package com.game.cricket.doa;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;

@Repository
public class SingletonConnection {

    private static String dbUser;
    private static String dbPassword;

    private static SingletonConnection single_instance = null;
    private Connection conn;


    @Value("${db.user}")
    public void setUser(String user){
        dbUser =user;
    }

    @Value("${db.password}")
    public void setPassword(String password){
        dbPassword=password;
    }

    public Connection getConn() {
        return this.conn;
    }

    private SingletonConnection() {
        try {
            //TODO Without Component not working
            System.out.println(dbUser + " " + dbPassword);
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cricket?autoReconnect=true&useSSL=false", dbUser, dbPassword);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static SingletonConnection getInstance() {
        if (single_instance == null)
            single_instance = new SingletonConnection();
        return single_instance;
    }
}
