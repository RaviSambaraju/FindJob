package com.job.findJob.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */

/**
 * Created by Ravi Sambaraju on 1/29/2019
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Long userId;

	private String email;

	private String mobile;

	private String name;

	private String password;

	private int pincode;

	//bi-directional many-to-one association to Permission
	@OneToMany(mappedBy="user")
	private List<Permission> permissions;

	public User() {
	}



	/**
	 * @return the userId
	 */


	public Long getUserId() {
		// TODO Auto-generated method stub
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	private void setUserId(Long userId) {
		this.userId = userId;
	}



	/**
	 * @param userId the userId to set
	 */


	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPincode() {
		return this.pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public List<Permission> getPermissions() {
		return this.permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public Permission addPermission(Permission permission) {
		getPermissions().add(permission);
		permission.setUser(this);

		return permission;
	}

	public Permission removePermission(Permission permission) {
		getPermissions().remove(permission);
		permission.setUser(null);

		return permission;
	}

}