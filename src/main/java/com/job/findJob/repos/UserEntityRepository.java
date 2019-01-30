package com.job.findJob.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.job.findJob.model.User;

/**
 * Created by Ravi Sambaraju on 1/29/2019
 */

public interface UserEntityRepository extends JpaRepository<User, Long>{
	User findByName(String userName);

	User findByEmail(String email);

	User findByMobile(String mobile);
}
