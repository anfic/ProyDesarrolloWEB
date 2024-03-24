package com.anfic.rest.APP_ProyDesarrolloWeb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anfic.rest.APP_ProyDesarrolloWeb.domain.Entidad_Registro;
import com.anfic.rest.APP_ProyDesarrolloWeb.domain.Hecho;
import com.anfic.rest.APP_ProyDesarrolloWeb.domain.Tipo_Violencia;

public interface HechoRepository extends JpaRepository<Hecho, Long>{
	List<Hecho> findByTipovio(Tipo_Violencia tipovio);
	List<Hecho> findByEntidad(Entidad_Registro entidad);
}
