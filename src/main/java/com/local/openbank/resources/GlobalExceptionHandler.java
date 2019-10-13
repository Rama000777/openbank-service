package com.local.openbank.resources;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.local.openbank.exception.ErrorDetails;

import feign.FeignException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(FeignException.class)
	public ResponseEntity<Object> handleFeignStatusException(FeignException exception, HttpServletResponse response,
			HttpServletRequest request) {
		response.setStatus(exception.status());
		return error(exception, HttpStatus.valueOf(exception.status()), request.getRequestURL());
	}

	private <E extends Exception> ResponseEntity<Object> error(final E exception, final HttpStatus httpStatus,
			final StringBuffer details) {
		return new ResponseEntity<>(new ErrorDetails(new Date(),
				Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName()), details.toString()),
				httpStatus);
	}

}
