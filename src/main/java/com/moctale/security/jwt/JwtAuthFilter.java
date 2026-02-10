package com.moctale.security.jwt;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.moctale.security.user.CustomUserDetailsService;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;
	private final CustomUserDetailsService userDetailsService;

	public JwtAuthFilter(JwtUtil jwtUtil, CustomUserDetailsService userDetailsService) {
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		 String token = Arrays.stream(Optional.ofNullable(request.getCookies()).orElse(new Cookie[0]))
	                .filter(c -> c.getName().equals("ACCESS_TOKEN"))
	                .map(Cookie::getValue)
	                .findFirst()
	                .orElse(null);

		 if (token != null) {
	            Claims claims = jwtUtil.parseToken(token);
	            String userId = claims.getSubject();

	            UserDetails userDetails =
	                    userDetailsService.loadUserByUsername(userId);

	            UsernamePasswordAuthenticationToken auth =
	                    new UsernamePasswordAuthenticationToken(
	                            userDetails, null, userDetails.getAuthorities());

	            SecurityContextHolder.getContext().setAuthentication(auth);
	        }

	        filterChain.doFilter(request, response);
	    }
}
