package com.job.findJob.security.model;

import java.io.Serializable;

/**
 * Created by Ravi Sambaraju on 1/29/2019
 */

public class JwtAuthenticationResponse implements Serializable {

	private static final long serialVersionUID = 1250166508152483573L;

	private String token;
	private JwtUser useobject;

	public JwtAuthenticationResponse(String token2, JwtUser user) {
		// TODO Auto-generated constructor stub
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public JwtUser getUseobject() {
		return useobject;
	}

	public void setUseobject(JwtUser useobject) {
		this.useobject = useobject;
	}

}
