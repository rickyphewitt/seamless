package com.rickyphewitt.seamless.services;

import com.rickyphewitt.seamless.data.SimpleResponse;
import org.springframework.stereotype.Service;

@Service
public class SimpleErrorService {

	public SimpleResponse success() {
		SimpleResponse error = new SimpleResponse();
		error.setSuccess(true);
		return error;
	}

	public SimpleResponse error(String errorString) {
		SimpleResponse error = new SimpleResponse();
		error.setSuccess(false);
		error.setError(errorString);
		return error;
	}
}
