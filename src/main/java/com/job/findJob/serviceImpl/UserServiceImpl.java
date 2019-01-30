package com.job.findJob.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.job.findJob.model.Permission;
import com.job.findJob.model.Role;
import com.job.findJob.model.User;
import com.job.findJob.repos.PermissionsEntityRepository;
import com.job.findJob.repos.UserEntityRepository;
import com.job.findJob.service.UserService;

/**
 * Created by Ravi Sambaraju on 1/29/2019
 */

@Service
public class UserServiceImpl implements UserService {

	private final UserEntityRepository userEntityRepository;
	private final PermissionsEntityRepository permissionsEntityRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserEntityRepository userEntityRepository,
			PermissionsEntityRepository permissionsEntityRepository,
			PasswordEncoder passwordEncoder) {
		this.userEntityRepository = userEntityRepository;
		this.permissionsEntityRepository = permissionsEntityRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	@Transactional
	public String addUser(User user) {
		User exit = userEntityRepository.findByMobile(user.getMobile());
		if (exit == null) {
			Permission permission = new Permission();
			Role role = new Role();
			role.setRoleId(2);
			role.setRoleName("USER");
			String encodedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encodedPassword);
			User user1 = userEntityRepository.saveAndFlush(user);
			permission.setUser(user1);
			permission.setRole(role);
			permissionsEntityRepository.save(permission);
			return user1.getName() + "  added successfully";
		}
		return "user already exist with this mobile number";
	}

}
