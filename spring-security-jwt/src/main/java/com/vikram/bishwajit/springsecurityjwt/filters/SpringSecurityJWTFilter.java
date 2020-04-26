package com.vikram.bishwajit.springsecurityjwt.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vikram.bishwajit.springsecurityjwt.service.MyUserDetailService;
import com.vikram.bishwajit.springsecurityjwt.util.SpringSecurityJWTUtil;

/**
 * Servlet Filter implementation class SpringSecurityJWTFilter
 * 
 * @author Bishwajit.
 */
@Component
public class SpringSecurityJWTFilter extends OncePerRequestFilter {

	@Autowired
	SpringSecurityJWTUtil springSecurityJWTUtil;

	@Autowired
	MyUserDetailService myUserDetailService;

	/**
	 * Default constructor.
	 */
	public SpringSecurityJWTFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwt = null;
		String username = null;

		// HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		final String authorizationHeader = request.getHeader("Authorization");
		
		if (null != authorizationHeader && authorizationHeader.startsWith("Bearer ")) {
			jwt = authorizationHeader.substring(7);
			username = springSecurityJWTUtil.extractUsername(jwt);
		}

		if (null != username && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			UserDetails userDetails = this.myUserDetailService.loadUserByUsername(username);
			
			if (springSecurityJWTUtil.validateToken(jwt, userDetails)) {	
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}

		// pass the request along the filter chain
		filterChain.doFilter(request, response);
	}
}
