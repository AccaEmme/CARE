package com.javainuse.springbootsecurity.controller;

import org.springframework.security.core.AuthenticationException;

public class ProvolaException extends AuthenticationException {
	// ~ Constructors
	// ===================================================================================================

	/**
	 * Constructs a <code>BadCredentialsException</code> with the specified message.
	 *
	 * @param msg the detail message
	 */
	public ProvolaException(String msg) {
		super(msg);
	}

	/**
	 * Constructs a <code>BadCredentialsException</code> with the specified message and
	 * root cause.
	 *
	 * @param msg the detail message
	 * @param t root cause
	 */
	public ProvolaException(String msg, Throwable t) {
		super(msg, t);
	}
}