package com.udemy.my_spring_react.category;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        Category category = new Category("cat1");
        Category category2 = new Category("cat2");

        return ResponseEntity.ok(Arrays.asList(category, category2));
    }
}
