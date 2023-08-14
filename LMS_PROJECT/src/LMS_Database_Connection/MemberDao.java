/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LMS_Database_Connection;

import Model.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class MemberDao {
    public List<Member> getAllUser() {
        List<Member> members = new ArrayList<Member>();
        
        Connection connection = JDBCConnection.getJDBCConnection();
        
        String sql = "SELECT * FROM members";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()) {
                Member member = new Member();
                
                member.setMemberID(rs.getInt("ID_MEMBER"));
                member.setFullName(rs.getString("FULL_NAME"));
                member.setDob(rs.getDate("DATE_OF_BIRTH"));
                member.setPhone(rs.getString("PHONE"));
                member.setStartDate(rs.getDate("START_DATE"));
                member.setEndDate(rs.getDate("END_DATE"));
                
                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return members;
    }
    
    public void addMember(Member member) {
        Connection connection = JDBCConnection.getJDBCConnection();
        
        String sql = "INSERT INTO members(FULL_NAME, DATE_OF_BIRTH, PHONE, START_DATE, END_DATE)"
                   + " VALUES(?,?,?,?,?)";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, member.getFullName());
            preparedStatement.setDate(2, member.getDob());
            preparedStatement.setString(3, member.getPhone());
            preparedStatement.setDate(4, member.getStartDate());
            preparedStatement.setDate(5, member.getEndDate());
            
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateMember(Member member) {
        Connection connection = JDBCConnection.getJDBCConnection();
        
        String sql = "UPDATE members SET FULL_NAME = ?,DATE_OF_BIRTH = ?,PHONE = ?," 
                   + "START_DATE = ?, END_DATE = ? WHERE ID_MEMBER = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, member.getFullName());
            preparedStatement.setDate(2, member.getDob());
            preparedStatement.setString(3, member.getPhone());
            preparedStatement.setDate(4, member.getStartDate());
            preparedStatement.setDate(5, member.getEndDate());
            preparedStatement.setInt(6, member.getMemberID());
            
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteMember(int id) {
        Connection connection = JDBCConnection.getJDBCConnection();
        
        String sql = "DELETE FROM members WHERE ID_MEMBER = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public Member getMemberByID(int id){
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "SELECT * FROM members WHERE ID_MEMBER = ?";
        Member member = new Member();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setInt(1, id);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {       
                member.setMemberID(rs.getInt("ID_MEMBER"));
                member.setFullName(rs.getString("FULL_NAME"));
                member.setDob(rs.getDate("DATE_OF_BIRTH"));
                member.setPhone(rs.getString("PHONE"));
                member.setStartDate(rs.getDate("START_DATE"));
                member.setEndDate(rs.getDate("END_DATE"));    
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return member;
    }
  
}
