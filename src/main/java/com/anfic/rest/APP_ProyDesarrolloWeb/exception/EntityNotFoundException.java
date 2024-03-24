
package com.anfic.rest.APP_ProyDesarrolloWeb.exception;

/**
 * Excepcion que se lanza cuando el proceso de busqueda no encuentra una entidad
 */
public class EntityNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String message) {
		super(message);
	}

}
