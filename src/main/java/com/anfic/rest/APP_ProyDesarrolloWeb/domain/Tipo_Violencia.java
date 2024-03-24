package com.anfic.rest.APP_ProyDesarrolloWeb.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Tipo_Violencia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idtipoviolencia;
	@Column(nullable = false,length = 45,unique = true)
	private String descripcion;
	@OneToMany(mappedBy = "tipovio")
	private List<Hecho> hecho=new ArrayList<>();
}
