package com.job.findJob.security.model.factory;

import com.job.findJob.model.Permission;
import com.job.findJob.model.User;
import com.job.findJob.repos.PermissionsEntityRepository;
import com.job.findJob.security.model.JwtUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ravi Sambaraju on 1/29/2019
 */
@Component
public class JwtUserFactory {

	private final PermissionsEntityRepository permissionsEntityRepository;

	@Autowired
	public JwtUserFactory(
			PermissionsEntityRepository permissionsEntityRepository) {
		this.permissionsEntityRepository = permissionsEntityRepository;

	}

	public JwtUser create(User user) {
		List<String> roles = getRoles(user.getUserId());
		Date lastPasswordReset = new Date(1523085196L);
		return new JwtUser( user.getUserId(), user.getName(),
				"Test firstname", "Test LastName", user.getEmail(),
				user.getPassword(), mapToGrantedAuthorities(roles), true,
				lastPasswordReset, "",
				user.getMobile(), user.getPincode());
	}

	private List<String> getRoles(Long objectId) {
		List<String> roles = new ArrayList<String>();
		List<Permission> authorities = permissionsEntityRepository
				.findByUserUserId(objectId);
		authorities.forEach(x -> {
			roles.add(x.getRole().getRoleName());
		});

		return roles;
	}

	private static List<GrantedAuthority> mapToGrantedAuthorities(
			List<String> authorities) {
		return authorities.stream()
				.map(authority -> new SimpleGrantedAuthority(authority))
				.collect(Collectors.toList());
	}
}
