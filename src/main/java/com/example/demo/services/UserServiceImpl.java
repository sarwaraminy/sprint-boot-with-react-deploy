package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.data.UserRepository;
import com.example.demo.data.Users;
import java.util.Optional;

@Service
public class UserServiceImpl implements UsersService {
    @Autowired
	private UserRepository userRepository;
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;

	//now implement our Users services that is initialize in UsersService
	@Override
	public List<Users> getAllUsers(){ //get all records from Users table
		Iterable<Users> iterable = userRepository.findAll();
		List<Users> UsersList = new ArrayList<>();
		iterable.forEach(UsersList::add);
		return UsersList;
	}
	
	@Override
	public Users getUserById(long id) { //Get data from Users table based on id
		return userRepository.findById(id).orElse(null);
	}
	
	@Override
	public Users saveUser(Users user) { // insert new records from form int Users table
		if(userRepository.findByEmail(user.getEmail()).isPresent()){
			throw new IllegalStateException("Email already exists");
		}
		user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
		return userRepository.save(user);
	}
	
	@Override
	public void deleteUser(long id) {
		userRepository.deleteById(id);
	}

	public Optional<Users> authenticateUser(String email, String password) {
        Optional<Users> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent() && passwordEncoder.matches(password, userOptional.get().getPasswordHash())) {
            return userOptional;
        } else {
            return Optional.empty();
        }
    }
}
