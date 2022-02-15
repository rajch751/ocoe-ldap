package com.uob.ocoe.ldap.ocoeldap.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;



@Component
public class JwtFilter extends OncePerRequestFilter{



    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authorization = httpServletRequest.getHeader("Authorization");
        String token = null;
        String userName = null;

		/*
		 * if(null != authorization && authorization.startsWith("Bearer ")) { token =
		 * authorization.substring(7); userName =
		 * jwtUtility.getUsernameFromToken(token); }
		 */

		/*
		 * if(null != userName && SecurityContextHolder.getContext().getAuthentication()
		 * == null) {
		 * 
		 * 
		 * 
		 * UsernamePasswordAuthenticationToken authentication = new
		 * UsernamePasswordAuthenticationToken(userName,null,null);
		 * 
		 * authentication.setDetails( new
		 * WebAuthenticationDetailsSource().buildDetails(httpServletRequest) );
		 * SecurityContextHolder.getContext().setAuthentication(authentication); }
		 */

        
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}

