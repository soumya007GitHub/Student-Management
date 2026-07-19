package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {
    public static Connection dbConnection() {
        Connection con = null;
        try {
            Class.forName("mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:/mysql:/localhost:3306/student_mgmt", "root", "root");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return con;
    }
}
