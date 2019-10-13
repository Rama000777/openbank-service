package com.local.openbank.clientapi;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CustomOpenBankRequestInterceptor implements RequestInterceptor {
	@Value("${openbank.api.directLoginPath}")
	private String directLoginPath;
	
	@Override
	public void apply(RequestTemplate template) {
		if (directLoginPath.equals(template.url())
				|| SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
			return;
		}
		template.header(HttpHeaders.AUTHORIZATION, String.format("DirectLogin token=%s",
				(String) SecurityContextHolder.getContext().getAuthentication().getCredentials()));
	}
}
