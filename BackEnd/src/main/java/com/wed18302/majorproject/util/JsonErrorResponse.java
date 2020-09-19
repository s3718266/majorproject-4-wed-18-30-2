package com.wed18302.majorproject.util;

import com.wed18302.majorproject.Authentication;

public class JsonErrorResponse extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JsonErrorResponse(String msg) {
		super(Authentication.generateErrorJson(msg));
	}
	
}
