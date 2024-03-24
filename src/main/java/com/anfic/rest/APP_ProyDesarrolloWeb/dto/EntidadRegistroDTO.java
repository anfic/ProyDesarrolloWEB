package com.anfic.rest.APP_ProyDesarrolloWeb.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EntidadRegistroDTO {
	private Long identidad;
	@NotBlank(message = "La descripcion no puede estar en blanco.")
	@Size(min = 5,message = "La entidad debe de tener al menos 5 caracteres")
	private String descripcion;
	@NotBlank(message = "La sigla no puede estar en blanco.")
	@Size(min = 2,message = "La entidad debe de tener al menos 2 siglas")
	private String siglas;
}
