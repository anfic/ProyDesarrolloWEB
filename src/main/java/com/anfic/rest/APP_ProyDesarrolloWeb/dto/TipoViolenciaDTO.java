package com.anfic.rest.APP_ProyDesarrolloWeb.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TipoViolenciaDTO {
	private Long idtipoviolencia;
	@NotBlank(message = "La sigla no puede estar en blanco.")
	@Size(min = 5,message = "El tipo de violencia debe de tener al menos 10 siglas")
	private String descripcion;

}
