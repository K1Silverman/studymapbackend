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
		
		Optional<User> user = userRepository.findUserBy(eMail);
		
		return user.isPresent();
	}

	public void createUser(RegisterUserDto user) {
		
		UserDto newUser = new UserDto();
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setEmail(user.getEmail());
		newUser.setPw(user.getPassword());
		newUser.setRole("User");
		newUser.setStatus("Active");
		
		User userEntity = userMapper.toEntity(newUser);
		userRepository.save(userEntity);
	}

	public LoginResponse findUserAndAuthenticate(UserDto loginUserDto) {
		User loginUserEntity = userMapper.toEntity(loginUserDto);
		Optional<User> user = userRepository.findUserBy(loginUserEntity.getEmail());
		if (user.isPresent()) {
			User foundUser = user.get();
			if (loginUserEntity.getPw() == foundUser.getPw()) {
				LoginResponse loginResponse = new LoginResponse();
				loginResponse.setFirstName(foundUser.getFirstname());
				loginResponse.setLastName(foundUser.getLastname());
				loginResponse.setEMail(foundUser.getEmail());
				loginResponse.setRole(foundUser.getRole());
				
				return loginResponse;
			} else {
				throw new AuthenticationFailedException(CustomError.INCORRECT_CREDENTIALS);
			}
		} else {
			throw new AuthenticationFailedException(CustomError.USER_DOES_NOT_EXIST);
		}
	}
}
