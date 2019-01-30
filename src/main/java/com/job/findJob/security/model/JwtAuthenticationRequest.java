package com.job.findJob.security.model;

import java.io.Serializable;

/**
 * Created by Ravi Sambaraju on 1/29/2019
 */
public class JwtAuthenticationRequest implements Serializable {

    private static final long serialVersionUID = -8445943548965154778L;

    private String email;
    private String password;

    public JwtAuthenticationRequest() {
        super();
    }

    public JwtAuthenticationRequest(String email, String password) {
        this.setUsername(email);
        this.setPassword(password);
    }

    public String getEmail() {
        return this.email;
    }

    public void setUsername(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
