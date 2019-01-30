package com.job.findJob.security.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.job.findJob.model.User;
import com.job.findJob.repos.UserEntityRepository;
import com.job.findJob.security.model.JwtUser;
import com.job.findJob.security.model.factory.JwtUserFactory;

/**
 * Created by Ravi Sambaraju on 1/29/2019
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {
	

    @Autowired
    private JwtUserFactory jwtUserFactory;

	private final UserEntityRepository userEntityRepository;

	@Autowired
	public CustomUserDetailsService(UserEntityRepository userEntityRepository) {
		this.userEntityRepository = userEntityRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {
		User user=userEntityRepository.findByEmail(email);
		if(Objects.isNull(user) )
            throw new UsernameNotFoundException("User is not found in object entities");
		JwtUser jwtUser = jwtUserFactory.create(user);
		return jwtUser;
	}

}
