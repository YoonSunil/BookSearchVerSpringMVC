package com.jp.software_s.database;

import java.sql.Connection;
import java.sql.DriverManager;


//DBConnection Setting
public class MariaDBConnect {
    private static Connection con;

    private MariaDBConnect() {
        con = connectToDB();
    }

    // SingletonHolder
    private static class Singleton {
        private static final MariaDBConnect instance = new MariaDBConnect();
    }
    public static MariaDBConnect getInstance() {
        return Singleton.instance;
    }

    public static Connection getConnection(){
        return con;
    }
    //DBConnect Check
    private static Connection connectToDB() {

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            //DB Address
            String jdbcUrl = "jdbc:mariadb://192.168.1.184:3306/ss_yoon?autoReconnect=true";
            //DB UserIntelligence
            String userId = "ss_yoon";
            String userPass = "ss_yoon";
            //DB Login
            con = DriverManager.getConnection(jdbcUrl, userId, userPass);
            System.out.println("database connect success");
            //DB Connetion return
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("failed");
        }
        return null;
    }

}
