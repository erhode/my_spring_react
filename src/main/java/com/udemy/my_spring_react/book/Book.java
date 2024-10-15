package com.udemy.my_spring_react.book;

import com.udemy.my_spring_react.category.Category;
import com.udemy.my_spring_react.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 20, message = "le titre doit faire entre 2 et 20 catactère")
    @NotBlank(message = "le titre ne peut pas être vide")
    private String title;

    private BookStatus status;

    @ManyToOne
    private Category categorie;

    @ManyToOne
    private User user;

    @Transient
    private int categoryId;

    private boolean deleted;

    public Book(User user, String title, BookStatus status, Category category ) {
        this.user = user;
        this.title = title;
        this.status = status;
        this.categorie = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategorie() {
        return categorie;
    }

    public void setCategorie(Category categorie) {
        this.categorie = categorie;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
