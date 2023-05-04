package com.enquiry.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.enquiry.demo.dto.ResponseData;
import com.enquiry.demo.entity.CustomUserDetails;
import com.enquiry.demo.entity.EnquiryStatus;
import com.enquiry.demo.request.UserRequest;
import com.enquiry.demo.service.EnquiryServiceImpl;
import com.enquiry.demo.service.UserServiceImpl;

@RestController
public class AuthorizationResource {
	
	 
	@Autowired
	private UserServiceImpl serviceImpl;

	@Autowired
	private EnquiryServiceImpl enServiceImpl;

	@PostMapping("/signup")
	public ResponseEntity<ResponseData> addUser(@RequestBody UserRequest userRequest) {
		UserRequest addUser = serviceImpl.addUser(userRequest);
		Map<String, Object> details = new HashMap<>();
		details.put("userEntity", addUser);
		return new ResponseEntity<ResponseData>(new ResponseData(HttpStatus.CREATED.toString(), details,
				"User created successfully. Please check email for Temparory Password"
						+ " or user password to reset to new Password"),
				HttpStatus.CREATED);
	}

	@PostMapping("/reset")
	public ResponseEntity<ResponseData> resetPassword(@RequestBody UserRequest userRequest) {
		boolean resetPassword = serviceImpl.resetPassword(userRequest);
		if (resetPassword) {
			return new ResponseEntity<ResponseData>(new ResponseData(HttpStatus.OK.toString(),
					"Password Reset is done pls continue to login with new password"), HttpStatus.OK);
		}
		return new ResponseEntity<ResponseData>(
				new ResponseData(HttpStatus.OK.toString(), "Email is not found with system records."), HttpStatus.OK);
	}

	@GetMapping("/signin")
	public ResponseEntity<ResponseData> loginUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Map<EnquiryStatus, Long> userEnGroupByEnrollStatus = enServiceImpl
				.getUserEnGroupByEnrollStatus(((CustomUserDetails) authentication.getPrincipal()).getUser());
		Map<String, Object> details = new HashMap<>();
		details.put("userEnquiry", userEnGroupByEnrollStatus);
		return new ResponseEntity<ResponseData>(new ResponseData(HttpStatus.OK.toString(), details, ""), HttpStatus.OK);
	}
}
