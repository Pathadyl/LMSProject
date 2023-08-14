/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LMS_Database_Connection;

import Model.Book;
import Model.Loan;
import Model.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class LoanDao {
    
    public List<Loan> getAllLoan() {
        List<Loan> loans = new ArrayList<Loan>();
        
        Connection connection = JDBCConnection.getJDBCConnection();
        
        String sql = "SELECT * FROM loans";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()) {
                Loan loan = new Loan();
                
                loan.setLoanId(rs.getInt("LOAN_ID"));
                loan.setIdBook(rs.getInt("ID_BOOK"));
                loan.setIdMember(rs.getInt("ID_MEMBER"));
                loan.setLoanDate(rs.getDate("LOAN_DATE"));
                loan.setDueDate(rs.getDate("DUE_DATE"));
                loan.setReturnDate(rs.getDate("RETURN_DATE"));
                
                loans.add(loan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return loans;
    }
    
    public void addLoan(Loan loan) {
        Connection connection = JDBCConnection.getJDBCConnection();

        String sql = "INSERT INTO loans(ID_BOOK, ID_MEMBER, LOAN_DATE, DUE_DATE, RETURN_DATE)"
                   + " VALUE(?,?,?,?,?)";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, loan.getIdBook());
            preparedStatement.setInt(2, loan.getIdMember());
            preparedStatement.setDate(3, loan.getLoanDate());
            preparedStatement.setDate(4, loan.getDueDate());
            preparedStatement.setDate(5, loan.getReturnDate());
            
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateLoan(Loan loan) {
        Connection connection = JDBCConnection.getJDBCConnection();
        
        String sql = "UPDATE loans SET ID_BOOk = ?,ID_MEMBER = ?,LOAN_DATE = ?," 
                   + "DUE_DATE = ?, RETURN_DATE = ? WHERE LOAN_ID = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, loan.getIdBook());
            preparedStatement.setInt(2, loan.getIdMember());
            preparedStatement.setDate(3, loan.getLoanDate());
            preparedStatement.setDate(4, loan.getDueDate());
            preparedStatement.setDate(5, loan.getReturnDate());
            preparedStatement.setInt(5, loan.getLoanId());
            
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void returnBook(int id, Date returnDate) {
        Connection connection = JDBCConnection.getJDBCConnection();
        
        String sql = "UPDATE loans SET RETURN_DATE = ? WHERE LOAN_ID = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, returnDate);
            preparedStatement.setInt(2, id);
            
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void borowBook(Loan loan) {
        Connection connection = JDBCConnection.getJDBCConnection();

        String sql = "INSERT INTO loans(ID_BOOK, ID_MEMBER, LOAN_DATE, DUE_DATE)"
                   + " VALUE(?,?,?,?)";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, loan.getIdBook());
            preparedStatement.setInt(2, loan.getIdMember());
            preparedStatement.setDate(3, loan.getLoanDate());
            preparedStatement.setDate(4, loan.getDueDate());
            
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Book> getBorrowBookedByMemberID(int memberID){
        List<Book> borrowedBooks = new ArrayList<Book>();
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "SELECT books.* FROM loans "
                + "JOIN books ON loans.ID_BOOK = books.ID_BOOK "
                + "WHERE loans.ID_MEMBER = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, memberID);
            ResultSet rs = preparedStatement.executeQuery();
           
            while (rs.next()) {
                Book book = new Book();
                
                book.setBookId(rs.getInt("ID_BOOk"));
                book.setBookName(rs.getString("NAME_BOOK"));
                book.setBookAuthor(rs.getString("NAME_AUTHOR"));
                book.setBookDescription(rs.getString("DESCRIPTION"));
                book.setYearPublic(rs.getString("YEAR_PUBLIC"));
                book.setAvailability(rs.getInt("AVAILABILITY"));
                
                borrowedBooks.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return borrowedBooks;
    }
    
    public List<Member> getMemberLoanByBookID(int bookID) {
        List<Member> loanMembers = new ArrayList<Member>();
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "SELECT members.* FROM loans "
                + "JOIN members ON loans.ID_MEMBER = members.ID_MEMBER "
                + "WHERE loans.ID_BOOK = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, bookID);
            ResultSet rs = preparedStatement.executeQuery();
           
            while (rs.next()) {
                Member member = new Member();
                
                member.setMemberID(rs.getInt("ID_MEMBER"));
                member.setFullName(rs.getString("FULL_NAME"));
                member.setDob(rs.getDate("DATE_OF_BIRTH"));
                member.setPhone(rs.getString("PHONE"));
                member.setStartDate(rs.getDate("START_DATE"));
                member.setEndDate(rs.getDate("END_DATE"));
                
                loanMembers.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return loanMembers;
    }
    
    public List<Loan> getMemberLoanTransactionByMemberID(int id) {
        List<Loan> memberLoanTransaction = new ArrayList<Loan>();
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "SELECT * FROM loans WHERE ID_MEMBER = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
           
            while (rs.next()) {
                Loan loan = new Loan();
                
                loan.setLoanId(rs.getInt("LOAN_ID"));
                loan.setIdBook(rs.getInt("ID_BOOK"));
                loan.setIdMember(rs.getInt("ID_MEMBER"));
                loan.setLoanDate(rs.getDate("LOAN_DATE"));
                loan.setDueDate(rs.getDate("DUE_DATE"));
                loan.setReturnDate(rs.getDate("RETURN_DATE"));
                
                memberLoanTransaction.add(loan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return memberLoanTransaction;
    }

    public List<Loan> getMemberLoanTransactionByBookID(int id) {
        List<Loan> memberLoanTransaction = new ArrayList<Loan>();
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "SELECT * FROM loans WHERE ID_BOOK = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
           
            while (rs.next()) {
                Loan loan = new Loan();
                
                loan.setLoanId(rs.getInt("LOAN_ID"));
                loan.setIdBook(rs.getInt("ID_BOOK"));
                loan.setIdMember(rs.getInt("ID_MEMBER"));
                loan.setLoanDate(rs.getDate("LOAN_DATE"));
                loan.setDueDate(rs.getDate("DUE_DATE"));
                loan.setReturnDate(rs.getDate("RETURN_DATE"));
                
                memberLoanTransaction.add(loan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return memberLoanTransaction;
    }
    
}

