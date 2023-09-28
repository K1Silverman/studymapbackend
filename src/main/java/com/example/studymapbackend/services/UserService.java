package com.example.studymapbackend.services;

import java.time.Instant;
import java.util.Optional;

import com.example.studymapbackend.repositories.RoleRepository;
import com.example.studymapbackend.repositories.mappers.RoleMapper;
import org.springframework.stereotype.Service;

import com.example.studymapbackend.dtos.user.LoginRequestDto;
import com.example.studymapbackend.dtos.user.LoginResponseDto;
import com.example.studymapbackend.dtos.user.RegisterUserDto;
import com.example.studymapbackend.dtos.user.UserDto;
import com.example.studymapbackend.entities.Session;
import com.example.studymapbackend.entities.user.User;
import com.example.studymapbackend.infrastructure.HashGenerator;
import com.example.studymapbackend.infrastructure.error.CustomError;
import com.example.studymapbackend.infrastructure.exception.AuthenticationFailedException;
import com.example.studymapbackend.repositories.SessionRepository;
import com.example.studymapbackend.repositories.UserRepository;
import com.example.studymapbackend.repositories.mappers.SessionMapper;
import com.example.studymapbackend.repositories.mappers.UserMapper;

import jakarta.annotation.Resource;


@Service
public class UserService {
	
	@Resource
	private UserRepository userRepository;
	
	@Resource
	private UserMapper userMapper;
	
	@Resource
	private ContentService contentService;
	
	@Resource
	private SessionRepository sessionRepository;
	
	@Resource
	private SessionMapper sessionMapper;

	@Resource
	private RoleRepository roleRepository;

	@Resource
	private RoleMapper roleMapper;
	
	
	public UserService(UserRepository userRepository, UserMapper userMapper, ContentService contentService,
					   SessionRepository sessionRepository, SessionMapper sessionMapper, RoleRepository roleRepository,
					   RoleMapper roleMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.contentService = contentService;
		this.sessionRepository = sessionRepository;
		this.sessionMapper = sessionMapper;
		this.roleRepository = roleRepository;
		this.roleMapper = roleMapper;
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
		newUser.setStatus("Active");
		
		User userEntity = userMapper.toEntity(newUser);
		userEntity.setRole(roleRepository.getRoleBy("User"));
		User newEntity = userRepository.save(userEntity);
		Integer newUserId = newEntity.getId();
		contentService.createDefaultFolder(newUserId);
	}

	public LoginResponseDto findUserAndAuthenticate(LoginRequestDto loginCredentials) throws AuthenticationFailedException {
		
		Optional<User> user = userRepository.findUserBy(loginCredentials.getEmail());
		System.out.println("user: " + user);
		System.out.println("user2: " + user.isPresent());
		if (user.isPresent()) {
			User foundUser = user.get();
			
			if (loginCredentials.getPassword().equals(foundUser.getPw())) {
				
				return setLoginResponseData(foundUser);
			} else {
				
				throw new AuthenticationFailedException(CustomError.INCORRECT_CREDENTIALS);
			}
		} else {
			System.out.println("j]uab siia?");
			throw new AuthenticationFailedException(CustomError.USER_DOES_NOT_EXIST);
		}
	}



	public LoginResponseDto getUserBy(Integer userId) {
		User user = userRepository.getReferenceById(userId);
		UserDto userDto = userMapper.toDto(user);

		return setLoginResponseData(userId, userDto);
	}
	
	private LoginResponseDto setLoginResponseData(User foundUser) {
		LoginResponseDto loginResponse = new LoginResponseDto();

		loginResponse.setId(foundUser.getId());
		loginResponse.setFirstName(foundUser.getFirstname());
		loginResponse.setLastName(foundUser.getLastname());
		loginResponse.setEMail(foundUser.getEmail());
		loginResponse.setRoleName(foundUser.getRole().getName());
		loginResponse.setStatus(foundUser.getStatus());
		loginResponse.setSessionHash(setActiveSessionHash(foundUser));
		
		return loginResponse;
	}

	private String setActiveSessionHash(User foundUser) {
		Optional<Session> existingActiveSession = sessionRepository.getUserActiveSession(foundUser.getId());
		
		if (existingActiveSession.isPresent()) {
			return existingActiveSession.get().getHash();
		} else {
			Session newSession = new Session();
			newSession.setHash(HashGenerator.genHash());
			newSession.setUserId(foundUser.getId());
			newSession.setStatus("Active");
			newSession.setDateTimeCreated(Instant.now());
			Session createdSession = sessionRepository.save(newSession);
			return createdSession.getHash();
		}
	}
	private LoginResponseDto setLoginResponseData(Integer userId, UserDto userDto) {
		LoginResponseDto loginResponse = new LoginResponseDto();
		loginResponse.setId(userId);
		loginResponse.setFirstName(userDto.getFirstName());
		loginResponse.setLastName(userDto.getLastName());
		loginResponse.setEMail(userDto.getEmail());
		loginResponse.setRoleName(userDto.getRole().getName());
		loginResponse.setStatus(userDto.getStatus());
		loginResponse.setSessionHash(setActiveSessionHash(userId, userDto));
		
		return loginResponse;
	}
	
	private String setActiveSessionHash(Integer userId, UserDto userDto) {
		Optional<Session> existingActiveSession = sessionRepository.getUserActiveSession(userId);
		
		if (existingActiveSession.isPresent()) {
			return existingActiveSession.get().getHash();
		} else {
			Session newSession = new Session();
			newSession.setHash(HashGenerator.genHash());
			newSession.setUserId(userId);
			newSession.setStatus("Active");
			newSession.setDateTimeCreated(Instant.now());
			Session createdSession = sessionRepository.save(newSession);
			return createdSession.getHash();
		}
	}

	public LoginResponseDto findActiveSessionOwner(String sessionHash) {
		Optional<Session> activeSession = sessionRepository.getSessionBy(sessionHash);
		if (activeSession.isPresent()) {
			User user = userRepository.getReferenceById(activeSession.get().getUserId());
			return setLoginResponseData(user);
		}
		// Siia peaks tulema error, kui sessioon on aegunud
		// Praegu sessiooni aegumise funktsionaalsust ei ole
		return null;
	}


}
