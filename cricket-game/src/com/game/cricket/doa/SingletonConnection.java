package com.game.cricket.doa;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingletonConnection {
    private static SingletonConnection single_instance = null;
    private Connection conn;

    public Connection getConn(){
        return this.conn;
    }

    private SingletonConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cricket?autoReconnect=true&useSSL=false", "root", "12345678");
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
