package com.job.findJob.repos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.job.findJob.model.Permission;

/**
 * Created by Ravi Sambaraju on 1/29/2019
 */

public interface PermissionsEntityRepository extends JpaRepository<Permission, Integer>{

	List<Permission> findByUserUserId(long userId);

}
