package com.rickyphewitt.seamless.data.exceptions;

public class TrackDoesNotExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TrackDoesNotExistException(String issue) {
		super(issue);
	}
}
