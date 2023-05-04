package com.enquiry.demo.request;

import java.io.Serializable;

import com.enquiry.demo.utils.AppConstant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1711203776070591641L;
	private Integer userId;
	private String name;
	private String email;
	private Integer contactNumber;
	private String password;
	@Builder.Default
	private String accStatus = AppConstant.ACC_LOCKED;
	private Integer roleId;
	private String resetPassword;
}
