
package com.kiet.hostel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() {

        try {

            String url = "jdbc:mysql://localhost:3306/hostel_db";
            String username = "root";
            String password = "root";

            Connection con = DriverManager.getConnection(
                    url, username, password
            );

            System.out.println("Database Connected Successfully!");

            return con;

        } catch (SQLException e) {

            System.out.println("Connection Failed!");
            System.out.println(e.getMessage());

            return null;
        }
    }
}
