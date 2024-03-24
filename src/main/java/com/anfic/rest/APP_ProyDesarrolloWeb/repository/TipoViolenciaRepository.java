package com.anfic.rest.APP_ProyDesarrolloWeb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anfic.rest.APP_ProyDesarrolloWeb.domain.Tipo_Violencia;

public interface TipoViolenciaRepository extends JpaRepository<Tipo_Violencia, Long>{
	Optional<Tipo_Violencia> findByDescripcion(String descripcion);
}
