package com.anfic.rest.APP_ProyDesarrolloWeb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anfic.rest.APP_ProyDesarrolloWeb.domain.Entidad_Registro;

public interface EntidadRegistroRepository extends JpaRepository<Entidad_Registro, Long> {
	Optional<Entidad_Registro> findByDescripcion(String descripcion);
	Optional<Entidad_Registro> findBySiglas(String siglas);
}
