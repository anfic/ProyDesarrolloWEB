package com.anfic.rest.APP_ProyDesarrolloWeb.service;

import java.util.List;

import com.anfic.rest.APP_ProyDesarrolloWeb.domain.Tipo_Violencia;
import com.anfic.rest.APP_ProyDesarrolloWeb.exception.EntityNotFoundException;
import com.anfic.rest.APP_ProyDesarrolloWeb.exception.IllegalOperationException;

public interface TipoViolenciaService {
	List<Tipo_Violencia> listarTodos();
	Tipo_Violencia buscarPorId(Long id);
	Tipo_Violencia grabar(Tipo_Violencia tipoViolencia)throws IllegalOperationException;
	Tipo_Violencia actualizar(Tipo_Violencia tipoViolencia, Long id) throws EntityNotFoundException,IllegalOperationException;
	void eliminar(Long id)throws EntityNotFoundException,IllegalOperationException;
	Tipo_Violencia buscarPorDescripcion(String descripcion);

}
