package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.turkraft.springfilter.boot.Filter;

import jakarta.validation.Valid;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.response.ResultPaginationDTO;
import com.example.demo.domain.response.user.ResCreateUserDTO;
import com.example.demo.domain.response.user.ResUpdateUserDTO;
import com.example.demo.domain.response.user.ResUserDTO;
import com.example.demo.service.UserService;
import com.example.demo.util.error.IdInvalidException;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class UserController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/users")
    public ResponseEntity<ResCreateUserDTO> createNewUser(@Valid @RequestBody User postManUser)
            throws IdInvalidException {

        if (this.userService.isEmailExist(postManUser.getEmail()) == true) {
            throw new IdInvalidException("Email da ton tai");
        }
        postManUser.setPassword(this.passwordEncoder.encode(postManUser.getPassword()));
        User newUser = this.userService.handleCreateUser(postManUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.convertToResCreateUserDTO(newUser));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) throws IdInvalidException {

        if (this.userService.handleGetUser(id) == null) {
            throw new IdInvalidException("User khong ton tai");
        }
        this.userService.handleDeleteUser(id);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<ResUserDTO> getUser(@PathVariable("id") long id) throws IdInvalidException {
        if (this.userService.handleGetUser(id) == null) {
            throw new IdInvalidException("User khong ton tai");
        }
        User user = this.userService.handleGetUser(id);
        return ResponseEntity.ok(this.userService.convertToResUserDTO(user));
    }

    @GetMapping("/users")
    public ResponseEntity<ResultPaginationDTO> getAllUser(@Filter Specification<User> spec, Pageable pageable) {
        return ResponseEntity.ok(this.userService.handleGetAllUser(spec, pageable));
    }

    @PutMapping("/users")
    public ResponseEntity<ResUpdateUserDTO> updateUser(@RequestBody User postmanUser) throws IdInvalidException {
        User updateUser = this.userService.handleUpdateUser(postmanUser);
        if(updateUser == null)
        {
            throw new IdInvalidException("User khong ton tai");
        }
        return ResponseEntity.ok(this.userService.convertToResUpdateUserDTO(updateUser));
    }

}
