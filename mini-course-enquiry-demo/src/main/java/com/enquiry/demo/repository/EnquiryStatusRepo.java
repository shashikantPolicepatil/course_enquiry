package com.enquiry.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enquiry.demo.entity.EnquiryStatus;

public interface EnquiryStatusRepo extends JpaRepository<EnquiryStatus, Integer> {

}
