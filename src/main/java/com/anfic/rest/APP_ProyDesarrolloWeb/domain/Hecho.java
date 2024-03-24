package com.anfic.rest.APP_ProyDesarrolloWeb.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class Hecho {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idhecho;
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date fechadelito;
	@ManyToOne()
	@JoinColumn(name = "identidad",nullable = true)
	private Entidad_Registro entidad;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Estado estado;
	@ManyToOne
	@JoinColumn(name="idtipoviolencia",nullable = true)
	private Tipo_Violencia tipovio;
}
