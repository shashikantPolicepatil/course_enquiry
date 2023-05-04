package com.enquiry.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enquiry.demo.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {

	public Optional<UserEntity> findByEmail(String email);

}
