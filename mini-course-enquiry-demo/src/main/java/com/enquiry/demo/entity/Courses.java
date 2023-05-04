package com.enquiry.demo.entity;

import javax.persistence.Column;
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
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "TBL_COURSES")
public class Courses {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="courseId")
	private Integer courseId;
	
	private String courseName;
	
	private String courseCode;
}
