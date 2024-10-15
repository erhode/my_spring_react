package com.udemy.my_spring_react.borrow;

import com.udemy.my_spring_react.book.Book;
import com.udemy.my_spring_react.category.Category;
import com.udemy.my_spring_react.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/borrows")
public class BorrowControlleur {

    @GetMapping
    public ResponseEntity findAllBorrows() {
        User borrower = new User();
        borrower.setFirstname("firstname borrower");
        borrower.setLastname("lastname borrower");
        borrower.setEmail("email borrower");
        borrower.setPassword("password borrower");

        User lender = new User();
        lender.setFirstname("firstname lender");
        lender.setLastname("lastname lender");
        lender.setEmail("email lender");
        lender.setPassword("password lender");

        Book book = new Book();
        book.setTitle("Book Title");
        book.setCategorie(new Category("category 1"));

        Borrow borrow = new Borrow(null, borrower, lender, book, LocalDate.now(), LocalDate.now().plusDays(30));

        Borrow borrow2 = new Borrow(null, borrower, lender, book, LocalDate.now(), LocalDate.now().plusDays(30));
        //new ResponseEntity(Arrays.asList(borrow, borrow2), HttpStatus.OK);
        return ResponseEntity.ok(Arrays.asList(borrow, borrow2));
    }

    @PostMapping
    public ResponseEntity createBorrow(@RequestBody Borrow borrow) {

        Borrow borrowCreated = new Borrow(null, null, null, null, LocalDate.now(), LocalDate.now().plusDays(30) );

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(borrowCreated.id())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{borrowId}")
    public ResponseEntity deleteBorrow(@PathVariable Long borrowId) {

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
