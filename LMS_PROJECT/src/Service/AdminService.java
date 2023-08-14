/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import LMS_Database_Connection.AdminDao;
import LMS_Database_Connection.BookDao;
import LMS_Database_Connection.LoanDao;
import LMS_Database_Connection.MemberDao;
import Model.Admin;
import Model.Book;
import Model.Loan;
import Model.Member;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class AdminService {
    private AdminDao adminDao;
    private MemberDao memberDao;
    private BookDao bookDao;
    private LoanDao loanDao;
    
    public AdminService() {
        adminDao = new AdminDao();
        memberDao = new MemberDao();
        bookDao = new BookDao();
        loanDao = new LoanDao();
    }
   
    //=====================Member Service ======================================
    public List<Admin> getAllAdmin() {
        
        return adminDao.getAllAdmin();
    }
    
    public List<Member> getAllMember() {
        return memberDao.getAllUser();
    }
    
    public void addMember (Member member) {
        memberDao.addMember(member);
    }
    
    public void deleteMember(int id) {
        memberDao.deleteMember(id);
    }
    
    public void updateMember(Member user) {
        memberDao.updateMember(user);
    }
    
    public Member searchMemberByID(int id){
        
        int l = 0, r = getAllMember().size()-1;
        
        while( l <= r) {
            int m = l + (r-l)/2;
            
            if (getAllMember().get(m).getMemberID() == id) {
                return getAllMember().get(m);
            }
            if (getAllMember().get(m).getMemberID() < id) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        
        return null;
    }
    
    public List<Member> searchMemberByName(String partialName){
        List<Member> searchedUserList = new ArrayList<Member>();
        
        for (Member member : getAllMember()) {
            if (member.getFullName().toLowerCase().contains(partialName.toLowerCase())) {
                searchedUserList.add(member);
            }
        }
        
        return searchedUserList;
    }
    
    public Member getMemberByID(int id) {
        return memberDao.getMemberByID(id);
    }
    
    public List<Loan> getMemberLoanTransactionByMemberID(int id) {
        return loanDao.getMemberLoanTransactionByMemberID(id);
    } 
    
    public List<Book> getBorrowBookedByMemberID(int id) {
        return loanDao.getBorrowBookedByMemberID(id);
    }
    
//==============================================================================
    
//===========================Book Service ======================================    
   
    public List<Book> getAllBook(){
        return bookDao.getAllBook();
    }
    
    public void addBook(Book book) {
        bookDao.addBook(book);
    }
    
    public void deleteBook(int id) {
        bookDao.deleteBook(id);
    }
    
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }
    
    public Book getBookByID(int id) {
        return bookDao.getBookByID(id);
    }
    
    public Book searchBookByID(int id) {
        int l = 0, r = getAllBook().size()-1;
        
        while( l <= r) {
            int m = l + (r-l)/2;
            
            if (getAllBook().get(m).getBookId() == id) {
                return getAllBook().get(m);
            }
            if (getAllBook().get(m).getBookId()< id) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        
        return null;
    }
    
    public List<Book> searchBookByName(String partialName) {
        List<Book> searchedBookList = new ArrayList<Book>();
        
        for (Book book : getAllBook()) {
            if (book.getBookName().toLowerCase().contains(partialName.toLowerCase())) {
                searchedBookList.add(book);
            }
        }
        
        return searchedBookList;
    }
    
    public List<Book> searchBookByGerne(String genre) {
        List<Book> searchedBookList = new ArrayList<Book>();
        
        for (Book book : getAllBook()) {
            if (book.getBookGerne().equals(genre)) {
                searchedBookList.add(book);
            }
        }
        return searchedBookList;
    }
    
    public List<Member> getMemberLoanByBookID(int bookID) {
        return loanDao.getMemberLoanByBookID(bookID);
    }
    
    public List<Loan> getMemberLoanTransactionByBookID(int id){
        return loanDao.getMemberLoanTransactionByBookID(id);
    }
 
//====================== Loan Service ==========================================
   public List<Loan> getAllLoan() {
       return loanDao.getAllLoan();
   }
   
   public void loanBook(Loan loan) {
       loanDao.borowBook(loan);
   }
   
   public void returnBook(int id, Date returnDate) {
       loanDao.returnBook(id, returnDate);
   }
    
}
