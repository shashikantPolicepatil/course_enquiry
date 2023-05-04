package com.enquiry.demo.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import com.enquiry.demo.entity.UserEnquiryEntity;
import com.enquiry.demo.entity.UserEntity;

public interface UserEnquireRepo extends JpaRepository<UserEnquiryEntity,Integer>{


}
