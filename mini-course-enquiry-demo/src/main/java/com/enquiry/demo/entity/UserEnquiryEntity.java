package com.enquiry.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@Table(name = "TBL_USER_ENQUIRY")
public class UserEnquiryEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String studentName;
	private Integer contactNum;
	private String classMode;
	//course name, user id
	@OneToOne
	@JoinColumn(name="enquiry_status_id")
	@Builder.Default
	private EnquiryStatus enquiryStatus=new EnquiryStatus();
	
	@OneToOne
	@JoinColumn(name="course_id")
	@Builder.Default
	private Courses couse=new Courses();
	
	@ManyToOne
	@JoinColumn(name="user_id")
	@Builder.Default
	private UserEntity user=new UserEntity();
}
