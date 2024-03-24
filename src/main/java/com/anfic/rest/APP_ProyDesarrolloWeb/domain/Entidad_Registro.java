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
public class Entidad_Registro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long identidad;
	@Column(unique = true,nullable = false,length = 45)
	private String descripcion;
	@Column(unique = true,nullable = false,length = 4)
	private String siglas;
	@OneToMany(mappedBy = "entidad")
	private List<Hecho> hecho=new ArrayList<>();
}
