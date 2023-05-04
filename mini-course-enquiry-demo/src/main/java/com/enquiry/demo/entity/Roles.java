package com.enquiry.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TBL_ROLES")
public class Roles {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer roleId;
	private String roleCode;
	private String roleName;
	
}
