package com.enquiry.demo.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.enquiry.demo.entity.Courses;
import com.enquiry.demo.entity.EnquiryStatus;
import com.enquiry.demo.entity.UserEnquiryEntity;
import com.enquiry.demo.entity.UserEntity;
import com.enquiry.demo.repository.UserEnquireRepo;
import com.enquiry.demo.request.UserEnquiryRequest;

@Service
public class EnquiryServiceImpl {
	
	@Autowired
	private UserEnquireRepo userEnquireRepo;

	public UserEnquiryRequest addOrEditEnquiry(UserEnquiryRequest userEnquiryRequest) {
		UserEnquiryEntity userEnquiryEntity = UserEnquiryEntity.builder().id(userEnquiryRequest.getId()!=null?userEnquiryRequest.getId():0)
				.classMode(userEnquiryRequest.getClassMode())
				.contactNum(userEnquiryRequest.getContactNum()).studentName(userEnquiryRequest.getStudentName())
				.user(UserEntity.builder().userId(userEnquiryRequest.getUserId()).build())
				.couse(Courses.builder().courseId(userEnquiryRequest.getCourseId()).build())
				.enquiryStatus(EnquiryStatus.builder().enquiryStatusId(userEnquiryRequest.getEnquiryId()).build())
				.build();

		UserEnquiryEntity save = userEnquireRepo.save(userEnquiryEntity);
		userEnquiryRequest.setId(save.getId());
		return userEnquiryRequest;
	}
	
	public List<UserEnquiryEntity> getAllEnquiriesByUser(UserEntity userEntity) {
		UserEnquiryEntity userEnquiryEntity = UserEnquiryEntity.builder().build();
		userEnquiryEntity.getUser().setUserId(userEntity.getUserId());
		/* Build Example and ExampleMatcher object */
		ExampleMatcher customExampleMatcher = ExampleMatcher.matchingAny()
				
				.withMatcher("user.userId", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

		Example<UserEnquiryEntity> customerPlanDataExample= Example.of(userEnquiryEntity, customExampleMatcher);

		/* Get based on search criteria*/
		return userEnquireRepo.findAll(customerPlanDataExample);
	}
	
	public Map<EnquiryStatus,Long> getUserEnGroupByEnrollStatus(UserEntity userEntity) {
		List<UserEnquiryEntity> allEnquiriesByUser = getAllEnquiriesByUser(userEntity);
		Map<EnquiryStatus, Long> collect = allEnquiriesByUser.stream().collect(Collectors.groupingBy(UserEnquiryEntity::getEnquiryStatus, Collectors.counting()));
		return collect;
	}
}
