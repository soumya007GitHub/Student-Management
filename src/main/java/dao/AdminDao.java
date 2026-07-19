package dao;

import config.DBConfig;
import model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao {
    private static final Connection con = DBConfig.dbConnection();

    public boolean adminLoginDao(Admin admin){
        try {
            String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, admin.getUsername());
            ps.setString(2, admin.getPassword());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
