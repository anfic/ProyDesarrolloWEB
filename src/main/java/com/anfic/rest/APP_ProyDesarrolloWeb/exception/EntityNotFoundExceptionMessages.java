/**
 * @file: ErrorMessage.java
 * @author: (c)2024 evalencia 
 * @created: Feb 15, 2024 1:42:34 PM
 */
package com.anfic.rest.APP_ProyDesarrolloWeb.exception;

/**
 * Clase de utilidad que contiene mensajes de error para excepciones de tipo EntityNotFoundException.
 */
public final class EntityNotFoundExceptionMessages {

	public static final String ETIDAD_REGISTRO_NOT_FOUND = "La entidad con id proporcionado no fue encontrado";
	public static final String TIPO_VIOLENCIA_NOT_FOUND = "El de violencia con el id proporcionado no fue encontrado";
	public static final String HECHO_NOT_FOUND = "El hecho con id proporcionado no fue encontrado";
	public static final String USUARIO_NOT_FOUND = "El usuario con id proporcionado no fue encontrado";
	
	
    // Constructor privado para evitar instanciación
	private EntityNotFoundExceptionMessages() {
		throw new IllegalStateException ("Utility class");
	}
}
