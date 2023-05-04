package com.enquiry.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.enquiry.demo.dto.ResponseData;
import com.enquiry.demo.entity.CustomUserDetails;
import com.enquiry.demo.entity.UserEnquiryEntity;
import com.enquiry.demo.request.UserEnquiryRequest;
import com.enquiry.demo.service.EnquiryServiceImpl;

@RestController
public class EnquiryResource {
	
	@Autowired
	private EnquiryServiceImpl enquiryServiceImpl;

	@PostMapping("/enquiry")
	public ResponseEntity<ResponseData> addEnquiry(@RequestBody UserEnquiryRequest userEnquiryRequest) {
		UserEnquiryRequest addEnquiry = enquiryServiceImpl.addOrEditEnquiry(userEnquiryRequest);
		HashMap<String,Object> hashMap = new HashMap<>();
		hashMap.put("userEnquiryentity", addEnquiry);
		return new ResponseEntity<ResponseData>(new ResponseData(HttpStatus.CREATED.toString(), hashMap, "Enquiry got added"),HttpStatus.CREATED);
	}
	
	@PutMapping("/enquiry/{id}")
	public ResponseEntity<ResponseData> addEnquiry(@RequestBody UserEnquiryRequest userEnquiryRequest,
			@PathVariable Integer id) {
		userEnquiryRequest.setId(id);
		UserEnquiryRequest addEnquiry = enquiryServiceImpl.addOrEditEnquiry(userEnquiryRequest);
		HashMap<String,Object> hashMap = new HashMap<>();
		hashMap.put("userEnquiryentity", addEnquiry);
		return new ResponseEntity<ResponseData>(new ResponseData(HttpStatus.CREATED.toString(), hashMap, "Enquiry got added"),HttpStatus.CREATED);
	}
	
	@GetMapping("/enquiry")
	public ResponseEntity<ResponseData> getAllEnquiryByUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		List<UserEnquiryEntity> allEnquiriesByUser = enquiryServiceImpl
				.getAllEnquiriesByUser(((CustomUserDetails)authentication.getPrincipal()).getUser());

		Map<String, Object> details = new HashMap<>();
		details.put("userEnquiry", allEnquiriesByUser);
		return new ResponseEntity<ResponseData>(new ResponseData(HttpStatus.OK.toString(), details, ""), HttpStatus.OK);
	}
}
