package com.enquiry.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.enquiry.demo.entity.CustomUserDetails;
import com.enquiry.demo.entity.UserEntity;
import com.enquiry.demo.repository.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> user = userRepo.findByEmail(username);
		if(!user.isPresent()) {
			throw new UsernameNotFoundException("User is not existed");
		} 
		return new CustomUserDetails(user.get());
	}


}
