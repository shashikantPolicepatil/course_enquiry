package com.enquiry.demo.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.enquiry.demo.entity.Roles;
import com.enquiry.demo.entity.UserEntity;
import com.enquiry.demo.exception.ResetPasswordException;
import com.enquiry.demo.repository.UserRepo;
import com.enquiry.demo.request.UserRequest;
import com.enquiry.demo.utils.AppConstant;
import com.enquiry.demo.utils.EmailUtils;
import com.enquiry.demo.utils.EquiryUtils;

@Service
public class UserServiceImpl {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JavaMailSender mailSender;
	
	public UserRequest addUser(UserRequest userRequest) {
		if(userRepo.findByEmail(userRequest.getEmail()).isPresent()) {
			throw new RuntimeException("User already existed with given Email.");
		}
		Roles role = Roles.builder().roleId(userRequest.getRoleId()).build();
		Set<Roles> roleSet = new HashSet<>();
		roleSet.add(role);
		UserEntity newUSer = UserEntity.builder().contactNumber(userRequest.getContactNumber())
				.email(userRequest.getEmail()).name(userRequest.getName()).roles(roleSet).build();
		String plainPassword = EquiryUtils.alphaNumericString(6);
		try {
			newUSer.setPassword(plainPassword);
			generateAndSendTempPass(newUSer);
		} catch (Exception e) {
			//throw new RuntimeException(e.getMessage());
		}
		newUSer = userRepo.save(newUSer);
		userRequest.setUserId(newUSer.getUserId());
		userRequest.setPassword(plainPassword);
		return userRequest;
	}
	
	public boolean resetPassword(UserRequest userRequest) {
		Optional<UserEntity> findByEmail = userRepo.findByEmail(userRequest.getEmail());
		if(findByEmail.isPresent() && findByEmail.get().getAccStatus().equals(AppConstant.ACC_LOCKED)) {
			UserEntity userEntity = findByEmail.get();
			boolean matches = passwordEncoder.matches(userRequest.getPassword(), userEntity.getPassword());
			if(matches){
				userEntity.setPassword(passwordEncoder.encode(userRequest.getResetPassword()));
				userEntity.setAccStatus(AppConstant.ACC_UN_LOCKED);
				userRepo.save(userEntity);
			} else {
				throw new ResetPasswordException("Given password is not matched with temp password or your account is unlocked. Please use correct temp password.");
			}
			return true;
		} return false;
	}
	private UserEntity generateAndSendTempPass(UserEntity newUser) throws Exception {
		String encode = passwordEncoder.encode(newUser.getPassword());
		newUser.setPassword(encode);
		EmailUtils.sendEmail(mailSender, newUser.getEmail(), "Temp Password to unlock account", 
				"Please user this temp password for reset your account and generate new password: "+newUser.getPassword());
		return newUser;
	}
}
