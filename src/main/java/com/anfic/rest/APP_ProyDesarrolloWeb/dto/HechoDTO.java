package com.anfic.rest.APP_ProyDesarrolloWeb.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.anfic.rest.APP_ProyDesarrolloWeb.domain.Entidad_Registro;
import com.anfic.rest.APP_ProyDesarrolloWeb.domain.Estado;
import com.anfic.rest.APP_ProyDesarrolloWeb.domain.Tipo_Violencia;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

@Data
public class HechoDTO {
	private Long idhecho;
	@PastOrPresent
	@NotNull(message = "La fecha de inicio de la investigación no puede estar vacía o no sigue el patron yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechadelito;
	private Estado estado;
	private Tipo_Violencia tipoViolencia;
	private Entidad_Registro entidadRegistro;
	
}
