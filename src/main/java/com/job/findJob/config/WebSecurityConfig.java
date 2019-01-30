package com.job.findJob.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.job.findJob.security.service.CustomUserDetailsService;
import com.job.findJob.securityEntry.JwtAuthenticationEntryPoint;
import com.job.findJob.securityEntry.JwtTokenUtil;

/**
 * Created by Ravi Sambaraju on 1/29/2019
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {



    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("Authorization")
    private String tokenHeader;

    @Value("/auth")
    private String authenticationPath;

    @Value("/api/v1/**")
    private String authenticationIgnorePath;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(this.customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }


    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring().antMatchers(HttpMethod.POST,authenticationPath)
                .and()
                .ignoring()
                .antMatchers(authenticationIgnorePath)
                .and()
                .ignoring()
                .antMatchers(HttpMethod.OPTIONS)
                .and()
                .ignoring()
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/**/*.eot",
                        "/**/*.woff2",
                        "/**/*.ttf",
                        "/**/*.woff",
                        "/**/*.json",
                        "/**/*.svg",
                        "/**/*.png",
                        "/**/*.jpg"

                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                .anyRequest().authenticated();

//        JwtAuthenticationTokenFilter authenticationTokenFilter = new JwtAuthenticationTokenFilter(customUserDetailsService, jwtTokenUtil, tokenHeader);
//        // Custom JWT based security filter
//        http
//                .addFilterBefore(authenticationTokenFilter,UsernamePasswordAuthenticationFilter.class);
//
//        // disable page caching
//        http
//                .headers().cacheControl();
    }
}
