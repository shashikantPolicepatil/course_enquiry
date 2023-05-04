package com.enquiry.demo.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import com.enquiry.demo.service.CustomUserDetailsService;

//@Component
public class CustomAuthFilter extends OncePerRequestFilter {

	
	 
	

	@Autowired
	private CustomUserDetailsService customUserDetails;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");
		String token = null;
		String userName = null;
		try {
			/*authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));*/
		} catch(Exception ex) {
			//throw new Exception("Invalid username or password");
		}
		/*if(authHeader!=null && authHeader.startsWith("Basic")) {
			token = authHeader.substring(7);
			try {
				userName = jwtUtil.extractUsername(token);
			} catch (Exception e) {
				throw new ServletException("Invalid token");
			}
		}
		
		if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails loadUserByUsername = customUserDetails.loadUserByUsername(userName);
			try {
				if(jwtUtil.validateToken(token, loadUserByUsername)) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loadUserByUsername, null, loadUserByUsername.getAuthorities());
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			} catch (Exception e) {
				throw new ServletException("Invalid token");
			}
		}*/
		filterChain.doFilter(request, response);
	}

}
