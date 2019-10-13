package com.local.openbank.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.local.openbank.clientapi.DirectAuthenticationClient;

import java.util.Collections;

@Component
public class CustomOpenBankAuthenticationProvider implements AuthenticationProvider {

	private DirectAuthenticationClient directAuthenticationClient;

	@Value("${openbank.consumerKey}")
	private String consumerKey;

	public CustomOpenBankAuthenticationProvider(DirectAuthenticationClient directAuthenticationClient) {
		this.directAuthenticationClient = directAuthenticationClient;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (authentication.isAuthenticated()) {
			return authentication;
		}
		try {
			return new UsernamePasswordAuthenticationToken(authentication.getName(),
					directAuthenticationClient.login(authentication.getName(),
							authentication.getCredentials().toString(), consumerKey),
					Collections.singleton(new SimpleGrantedAuthority("USER")));
		} catch (Exception exception) {
			throw new BadCredentialsException(exception.getMessage());
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
