/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LMS_Database_Connection;

import Model.Book;
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
public class BookDao {
    public List<Book> getAllBook() {
        List<Book> books = new ArrayList<Book>();
        
        Connection connection = JDBCConnection.getJDBCConnection();
        
        String sql = "SELECT * FROM books";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()) {
                Book book = new Book();
                
                book.setBookId(rs.getInt("ID_BOOk"));
                book.setBookName(rs.getString("NAME_BOOK"));
                book.setBookAuthor(rs.getString("NAME_AUTHOR"));
                book.setBookDescription(rs.getString("DESCRIPTION"));
                book.setBookGerne(rs.getString("GENRE"));
                book.setYearPublic(rs.getString("YEAR_PUBLIC"));
                book.setAvailability(rs.getInt("AVAILABILITY"));
                
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return books;
    }
    
    public void addBook(Book book) {
        Connection connection = JDBCConnection.getJDBCConnection();
        
        String sql = "INSERT INTO books(NAME_BOOK, NAME_AUTHOR, DESCRIPTION, GENRE, YEAR_PUBLIC, AVAILABILITY)"
                   + " VALUE(?,?,?,?,?,?)";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, book.getBookName());
            preparedStatement.setString(2, book.getBookAuthor());
            preparedStatement.setString(3, book.getBookDescription());
            preparedStatement.setString(4, book.getBookGerne());
            preparedStatement.setString(5, book.getYearPublic());
            preparedStatement.setInt(6, book.getAvailability());
            
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateBook(Book book) {
        Connection connection = JDBCConnection.getJDBCConnection();
        
        String sql = "UPDATE books SET NAME_BOOK = ?,NAME_AUTHOR = ?,DESCRIPTION = ?," 
                   + "GENRE = ?,YEAR_PUBLIC = ?,AVAILABILITY = ? WHERE ID_BOOK = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, book.getBookName());
            preparedStatement.setString(2, book.getBookAuthor());
            preparedStatement.setString(3, book.getBookDescription());
            preparedStatement.setString(4, book.getBookGerne());
            preparedStatement.setString(5, book.getYearPublic());
            preparedStatement.setInt(6, book.getAvailability());
            preparedStatement.setInt(7, book.getBookId());
            
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
           
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    }
    
    public void deleteBook(int id) {
        Connection connection = JDBCConnection.getJDBCConnection();
        
        String sql = "DELETE FROM books WHERE ID_BOOk = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
 
        } catch (SQLException e) {
            e.printStackTrace();
        }   
    }
    
    public Book getBookByID(int id) {
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "SELECT * FROM books WHERE ID_BOOK = ?";
        Book book = new Book();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {       
                book.setBookId(rs.getInt("ID_BOOk"));
                book.setBookName(rs.getString("NAME_BOOK"));
                book.setBookAuthor(rs.getString("NAME_AUTHOR"));
                book.setBookDescription(rs.getString("DESCRIPTION"));
                book.setBookGerne(rs.getString("GENRE"));
                book.setYearPublic(rs.getString("YEAR_PUBLIC"));
                book.setAvailability(rs.getInt("AVAILABILITY"));    
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return book;
    }
}
