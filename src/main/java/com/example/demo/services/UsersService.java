package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.data.Users;

public interface UsersService {
    List<Users> getAllUsers();
	Users getUserById(long id);
	Users saveUser(Users user);
	void deleteUser(long id);
    Optional<Users> authenticateUser(String email, String password);
}
