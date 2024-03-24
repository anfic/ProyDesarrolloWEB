package com.anfic.rest.APP_ProyDesarrolloWeb.service;

import java.util.List;

import com.anfic.rest.APP_ProyDesarrolloWeb.domain.Entidad_Registro;
import com.anfic.rest.APP_ProyDesarrolloWeb.exception.EntityNotFoundException;
import com.anfic.rest.APP_ProyDesarrolloWeb.exception.IllegalOperationException;

public interface EntidadRegistroService  {
	List<Entidad_Registro> listarTodos();
	Entidad_Registro buscarPorId(Long id);
	Entidad_Registro grabar(Entidad_Registro entidadRegistro)throws IllegalOperationException;
	Entidad_Registro actualizar(Entidad_Registro entidadRegistro, Long id) throws EntityNotFoundException,IllegalOperationException;
	void eliminar(Long id)throws EntityNotFoundException,IllegalOperationException;
	Entidad_Registro buscarPorDescripcion(String descripcion);
	Entidad_Registro buscarPorSiglas(String siglas);

}
