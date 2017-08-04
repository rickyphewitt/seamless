package com.rickyphewitt.seamless.data.exceptions;

public class ConnectionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ConnectionException(String issue) {
		super(issue);
	}

}
