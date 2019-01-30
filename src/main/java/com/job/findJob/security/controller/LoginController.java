package com.job.findJob.security.controller;

import java.util.Objects;
import javax.security.sasl.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.job.findJob.security.model.JwtAuthenticationRequest;
import com.job.findJob.security.model.JwtAuthenticationResponse;
import com.job.findJob.security.model.JwtUser;
import com.job.findJob.security.service.CustomUserDetailsService;
import com.job.findJob.securityEntry.JwtTokenUtil;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@PostMapping(value = "/auth")
	public JwtAuthenticationResponse createAuthenticationToken(
			@RequestBody JwtAuthenticationRequest authenticationRequest)
			throws AuthenticationException {
		authenticate(authenticationRequest.getEmail(),
				authenticationRequest.getPassword());
		final UserDetails userDetails = customUserDetailsService
				.loadUserByUsername(authenticationRequest.getEmail());
		JwtUser user = (JwtUser) userDetails;
		String type = "email";
		final String token = jwtTokenUtil.generateToken(userDetails, type);
		JwtAuthenticationResponse response = new JwtAuthenticationResponse(
				token, user);
		response.setUseobject(user);
		response.setToken(token);
		return response;
	}

	private void authenticate(String email, String password)
			throws AuthenticationException {
		Objects.requireNonNull(email);
		Objects.requireNonNull(password);

		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(
							email, password));
		} catch (DisabledException e) {
			throw new AuthenticationException("User is disabled!", e);
		} catch (BadCredentialsException e) {
			throw new AuthenticationException("Bad credentials!", e);
		}
	}
}
