package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dao {

    public Connection getConnection() throws Exception {
     
        Class.forName("org.postgresql.Driver");

 
        String url = "jdbc:postgresql://localhost:5432/kaadaidb";
        String user = "postgres";
        String password = "password"; 


        return DriverManager.getConnection(url, user, password);
    }
}
