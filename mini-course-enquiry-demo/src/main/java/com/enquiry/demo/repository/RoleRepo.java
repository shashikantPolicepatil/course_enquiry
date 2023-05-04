package com.enquiry.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enquiry.demo.entity.Roles;

public interface RoleRepo extends JpaRepository<Roles, Integer> {

}
