package com.enquiry.demo.entity;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.enquiry.demo.utils.AppConstant;

public class CustomUserDetails implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5689129067296890403L;
	private UserEntity user;
	
	public CustomUserDetails(UserEntity user) {
		this.user=user;
	}

	public UserEntity getUser() {
		return this.user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		Set<Roles> roles = this.user.getRoles();
		return roles.stream().map((role)->new SimpleGrantedAuthority(role.getRoleCode())).collect(Collectors.toList());
		/*List<SimpleGrantedAuthority> arrayList = new ArrayList<>();
		for (Roles role : roles) {
			arrayList.add(new SimpleGrantedAuthority(role.getRoleCode()));
		}
		return arrayList;*/
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.user.getAccStatus().equals(AppConstant.ACC_UN_LOCKED)?true:false;
	}

}
