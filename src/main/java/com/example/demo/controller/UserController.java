package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FrontEndServer;
import com.example.demo.data.LoginRequest;
import com.example.demo.data.Users;
import com.example.demo.services.UsersService;

@RestController
@RequestMapping(path="/auth")
@CrossOrigin(origins = FrontEndServer.FRONT_END_SERVER_ADDRESS)
public class UserController {

    @Autowired private UsersService userService;

    @GetMapping("/users")
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/signup")
    public ResponseEntity<Users> registerUser(@RequestBody Users user) {
        Users registerUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        System.out.println("password!---: "+ loginRequest.getEmail()+" "+ loginRequest.getPassword());
        Optional<Users> userOptional = userService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
}
