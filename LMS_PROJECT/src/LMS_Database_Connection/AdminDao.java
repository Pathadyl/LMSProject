/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LMS_Database_Connection;

import Model.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {
    
    public List<Admin> getAllAdmin() {
        List<Admin> admins = new ArrayList<Admin>();
        
        Connection connection = JDBCConnection.getJDBCConnection();
        
        String sql = "SELECT * FROM admin";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()) {
                Admin admin = new Admin();
                
                admin.setUserName(rs.getString("USER_NAME"));
                admin.setPassword(rs.getString("PASSWORD"));
                admin.setAdminID(rs.getInt("ID_ADMIN"));
                
                admins.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return admins;
    } 
}