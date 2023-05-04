package com.enquiry.demo.config;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.enquiry.demo.entity.Courses;
import com.enquiry.demo.entity.EnquiryStatus;
import com.enquiry.demo.entity.Roles;
import com.enquiry.demo.repository.CourseRepo;
import com.enquiry.demo.repository.EnquiryStatusRepo;
import com.enquiry.demo.repository.RoleRepo;

@Component
public class DataLoader implements CommandLineRunner {
	
	@Autowired
	private EnquiryStatusRepo enStatusRepo;
	
	@Autowired
	private CourseRepo courseRepo;
	
	@Autowired
	private RoleRepo roleRepo;

	@Override
	public void run(String... args) throws Exception {
		List<EnquiryStatus> collect = Stream.of(EnquiryStatus.builder().entityStatus("New").build()
				,EnquiryStatus.builder().entityStatus("Lost").build()
				,EnquiryStatus.builder().entityStatus("Enrolled").build()).collect(Collectors.toList());
		enStatusRepo.saveAll(collect);

		List<Courses> courseList = Stream
				.of(Courses.builder().courseName("Java").courseCode("JV").build(), Courses.builder()
						.courseName("Spring Boot and Microservices").courseCode("SPR_BOOT_MICRO_SER").build())
				.collect(Collectors.toList());
		
		courseRepo.saveAll(courseList);
		
		List<Roles> rolesList = Stream
				.of(Roles.builder().roleCode("USER").roleName("User").build(), 
						Roles.builder().roleCode("ADMIN").roleName("Admin").build())
				.collect(Collectors.toList());
		
		roleRepo.saveAll(rolesList);
		
	}

}
