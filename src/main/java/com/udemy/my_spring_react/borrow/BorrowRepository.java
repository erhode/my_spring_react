package com.udemy.my_spring_react.borrow;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends CrudRepository<Borrow, Long> {

    List<Borrow> findByBorrowerId(Long borrowerId);
    List<Borrow> findByBookId(Long bookId);

}
