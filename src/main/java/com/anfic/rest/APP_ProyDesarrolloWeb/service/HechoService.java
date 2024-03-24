package com.anfic.rest.APP_ProyDesarrolloWeb.service;

import java.util.List;

import com.anfic.rest.APP_ProyDesarrolloWeb.domain.Hecho;
import com.anfic.rest.APP_ProyDesarrolloWeb.exception.EntityNotFoundException;
import com.anfic.rest.APP_ProyDesarrolloWeb.exception.IllegalOperationException;

public interface HechoService {
	List<Hecho> listarTodos();
	Hecho buscarPorId(Long id);
	Hecho grabar(Hecho hecho) throws IllegalOperationException;
	Hecho actualizar(Hecho hecho, Long id) throws EntityNotFoundException, IllegalOperationException;
    void eliminar(Long i) throws EntityNotFoundException, IllegalOperationException;
    public Hecho asignarViolencia(Long idhecho,Long idtipo)throws EntityNotFoundException, IllegalOperationException;
    public Hecho asignarEntidad(Long idhecho,Long identidad)throws EntityNotFoundException, IllegalOperationException;
}
