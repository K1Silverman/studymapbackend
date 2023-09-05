package com.example.studymapbackend.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.studymapbackend.dtos.LoginResponse;
import com.example.studymapbackend.dtos.RegisterUserDto;
import com.example.studymapbackend.dtos.UserDto;
import com.example.studymapbackend.entities.User;
import com.example.studymapbackend.infrastructure.error.CustomError;
import com.example.studymapbackend.infrastructure.exception.AuthenticationFailedException;
import com.example.studymapbackend.repositories.UserRepository;
import com.example.studymapbackend.repositories.mappers.UserMapper;

import jakarta.annotation.Resource;


@Service
public class UserService {
	
	@Resource
	private UserRepository userRepository;
	
	@Resource
	private UserMapper userMapper;
	
	public UserService(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}
	
	public Boolean checkEmailAddressExists(String eMail) {
		
		String email = eMail.toLowerCase().strip();
		Optional<User> user = userRepository.findUserBy(email);
		
		return user.isPresent();
	}

	public void createUser(RegisterUserDto user) {
		
		UserDto newUser = new UserDto();
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setEmail(user.getEmail().toLowerCase().strip());
		newUser.setPw(user.getPassword());
		newUser.setRole("User");
		newUser.setStatus("Active");
		
		User userEntity = userMapper.toEntity(newUser);
		userRepository.save(userEntity);
	}

	public LoginResponse findUserAndAuthenticate(String email, String password) {
		Optional<User> user = userRepository.findUserBy(email);
		if (user.isPresent()) {
			User foundUser = user.get();
			if (password.equals(foundUser.getPw())) {
				LoginResponse loginResponse = new LoginResponse();
				loginResponse.setId(foundUser.getId());
				loginResponse.setFirstName(foundUser.getFirstname());
				loginResponse.setLastName(foundUser.getLastname());
				loginResponse.setEMail(foundUser.getEmail());
				loginResponse.setRole(foundUser.getRole());
				loginResponse.setStatus(foundUser.getStatus());
				
				return loginResponse;
			} else {
				throw new AuthenticationFailedException(CustomError.INCORRECT_CREDENTIALS);
			}
		} else {
			throw new AuthenticationFailedException(CustomError.USER_DOES_NOT_EXIST);
		}
	}
}
