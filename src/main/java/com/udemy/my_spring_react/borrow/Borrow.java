package com.udemy.my_spring_react.borrow;

import com.udemy.my_spring_react.book.Book;
import com.udemy.my_spring_react.user.User;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate borrowDate;

    private LocalDate returnDate;

    @ManyToOne
    @JoinColumn(name = "id")
    private User borrower;

    @ManyToOne
    @JoinColumn(name = "id")
    private User lender;

    @ManyToOne
    @JoinColumn(name = "id")
    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getLender() {
        return lender;
    }

    public void setLender(User lender) {
        this.lender = lender;
    }

    public User getBorrower() {
        return borrower;
    }

    public void setBorrower(User borrower) {
        this.borrower = borrower;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}

