package com.udemy.my_spring_react.user;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping
    public ResponseEntity addUser(@Valid @RequestBody User user) {
        User userCreated = new User();
        userCreated.setFirstname(user.getFirstname());
        userCreated.setLastname(user.getLastname());
        userCreated.setEmail(user.getEmail());
        userCreated.setPassword(user.getPassword());

        //TODO PESIST USER
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated)
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
