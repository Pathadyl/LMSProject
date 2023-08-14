/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.List;

/**
 *
 * @author Admin
 */
public class Book {
    private int bookId;
    private String bookName;
    private String bookAuthor;
    private String bookDescription;
    private String bookGerne;
    private String yearPublic;
    private int availability;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getBookGerne() {
        return bookGerne;
    }

    public void setBookGerne(String bookGerne) {
        this.bookGerne = bookGerne;
    }

    public String getYearPublic() {
        return yearPublic;
    }

    public void setYearPublic(String yearPublic) {
        this.yearPublic = yearPublic;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }
    
    
}
