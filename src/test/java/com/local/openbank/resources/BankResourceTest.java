package com.local.openbank.resources;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.servlet.ServletContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.local.openbank.dto.UserDto;
import com.local.openbank.model.Bank;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Slf4j
public class BankResourceTest {

	@LocalServerPort
	int randomServerPort;

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void testEmployee() throws Exception {
		final String baseUrl = "http://localhost:" + randomServerPort + servletContext.getContextPath();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

		HttpEntity<UserDto> request = new HttpEntity<>(null, headers);
		final ResponseEntity<List<Bank>> banksResponse = testRestTemplate.exchange(baseUrl + "/banks", HttpMethod.GET,
				request, new ParameterizedTypeReference<List<Bank>>() {
				});
		log.debug("Total Banks Size" + banksResponse.getBody().size());
		assertTrue("Expected to match", banksResponse.getStatusCode().equals(HttpStatus.OK));
	}
}