package com.example.library.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "borrow")
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String author;
    private String ISBN;
    private String genre;
    private Long previousUserId;
    private int returnTime;
    private Date borrowDate;


    @ManyToOne
    @JoinColumn(name = "librarian_id")
    private Librarian librarian;



    @PrePersist
    protected void onCreate() {
        borrowDate = new Date(); // Set the create date to the current date and time when an entity is persisted.
    }

    public Borrow(String name, String author, String ISBN, String genre, Long previousUserId, Date borrowDate,int returnTime, Librarian librarian) {
        super();
        this.name = name;
        this.author = author;
        this.ISBN = ISBN;
        this.genre = genre;
        this.previousUserId = previousUserId;
        this.borrowDate = borrowDate;
        this.returnTime = returnTime;
        this.librarian = librarian;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Borrow() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPreviousUserId() {
        return previousUserId;
    }

    public void setPreviousUserId(Long previousUserId) {
        this.previousUserId = previousUserId;
    }
    public int getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(int returnTime) {
        this.returnTime = returnTime;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public Librarian getLibrarian() {
        return librarian;
    }

    public void setLibrarian(Librarian librarian) {
        this.librarian = librarian;
    }
}
