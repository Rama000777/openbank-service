package com.local.openbank.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@Slf4j
public class WebSecurityConfigurerConfig extends WebSecurityConfigurerAdapter {

	private AuthenticationProvider authenticationProvider;

	public WebSecurityConfigurerConfig(AuthenticationProvider authProvider) {
		this.authenticationProvider = authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
		auth.eraseCredentials(true);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.debug(
				"Using default configure(HttpSecurity). If subclassed this will potentially override subclass configure(HttpSecurity).");
		http.csrf().disable();
		http.headers().frameOptions().disable();
		http.authorizeRequests().anyRequest().permitAll();
	}
}
