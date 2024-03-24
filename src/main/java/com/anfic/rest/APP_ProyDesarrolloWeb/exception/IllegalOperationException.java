package com.anfic.rest.APP_ProyDesarrolloWeb.exception;

/**
 * Excepcion que se lanza cuando se realiza una operacion ilegal
 */
public class IllegalOperationException extends Exception {

	private static final long serialVersionUID = 1L;

	public IllegalOperationException(String message) {
		super (message);
	}

}
