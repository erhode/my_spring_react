package com.udemy.my_spring_react.book;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book>  findByStatusAndUserIdNotAndDeletedFalse(BookStatus status, Long userId);
    List<Book> findByUserIdAndDeletedFalse(Long userId);
}
