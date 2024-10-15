package com.udemy.my_spring_react.book;

import com.udemy.my_spring_react.category.Category;
import com.udemy.my_spring_react.category.CategoryRepository;
import com.udemy.my_spring_react.user.User;
import com.udemy.my_spring_react.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity findAll(@RequestParam(required = false) BookStatus status ) {
        List<Book> books;
        Long userConnectedId = getUserConnectedId();

        //free books
        if (status != null && status.equals(BookStatus.FREE)) {
            books = bookRepository.findByStatusAndUserIdNotAndDeletedFalse(status, userConnectedId);
        } else {
            //my books
            books = bookRepository.findByUserIdAndDeletedFalse(userConnectedId);

        }

//        Book book = new Book();
//        book.setTitle("Book Title");
//        book.setCategorie(new Category("category 1"));
//
//        Book book2 = new Book();
//        book2.setTitle("Book Title 2");
//        book2.setCategorie(new Category("category 2"));
//
//        Book book3 = new Book();
//        book3.setTitle("Book Title 3");
//        book3.setCategorie(new Category("category 3"));

//        Book book4 = bookRepository.findById(1L).orElseGet(null);

//        return new ResponseEntity(Arrays.asList(book, book3, book2), HttpStatus.OK);
        return new ResponseEntity(books, HttpStatus.OK);
    }

    Long getUserConnectedId() {
        return 1L;
    }

    @GetMapping("/{bookId}")
    public ResponseEntity  findById(@PathVariable Long bookId) {
        Book book = new Book();
        book.setTitle("Book Title");
        book.setCategorie(new Category("category 1"));

//        return new ResponseEntity(Arrays.asList(book), HttpStatus.OK);
        return  ResponseEntity.ok(book);
    }

    @PostMapping
    public ResponseEntity createBook(@RequestBody @Valid Book newBookRequest, UriComponentsBuilder ucb) {
        //TODO PERSIST BOOK

//        Book savedBook = new Book();
//        savedBook.setTitle(book.getTitle());
//        savedBook.setCategorie(book.getCategorie());
//        savedBook.setStatus(book.getStatus());

        Optional<User> userconnected = userRepository.findById(getUserConnectedId());

        Book bookWithOwner = new Book(userconnected.get(), newBookRequest.getTitle(), newBookRequest.getStatus(), newBookRequest.getCategorie());
        Optional<Category> category = categoryRepository.findById(newBookRequest.getId());

        if (category.isPresent()) {
            bookWithOwner.setCategorie(category.get());
        } else {
            return new ResponseEntity("Category invalid", HttpStatus.BAD_REQUEST);
        }

        if (userconnected.isPresent()) {
            bookWithOwner.setUser(userconnected.get());
        } else {
            return new ResponseEntity("User invalid", HttpStatus.BAD_REQUEST);
        }

        bookWithOwner.setDeleted(false);
        bookWithOwner.setStatus(BookStatus.FREE);

        Book savedBook = bookRepository.save(bookWithOwner);


        URI locationOfNewCashCard = ucb //FOWARD
                .path("book/{id}")
                .buildAndExpand(savedBook.getId())
                .toUri();

        //  return ResponseEntity.ok(Arrays.asList(book));
        return ResponseEntity.created( locationOfNewCashCard).build();
    }

    @PutMapping("/{bookId}")
    public ResponseEntity updateBook(@PathVariable String bookId, @RequestBody Book book) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity deleteBook(@PathVariable String bookId) { //RequestParam == ?bookid=xxx
        //TODO delete BOOK
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
