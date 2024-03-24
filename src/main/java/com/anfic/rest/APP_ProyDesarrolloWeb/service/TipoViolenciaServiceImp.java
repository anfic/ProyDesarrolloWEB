package com.anfic.rest.APP_ProyDesarrolloWeb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anfic.rest.APP_ProyDesarrolloWeb.domain.Tipo_Violencia;
import com.anfic.rest.APP_ProyDesarrolloWeb.exception.EntityNotFoundException;
import com.anfic.rest.APP_ProyDesarrolloWeb.exception.EntityNotFoundExceptionMessages;
import com.anfic.rest.APP_ProyDesarrolloWeb.exception.IllegalOperationException;
import com.anfic.rest.APP_ProyDesarrolloWeb.repository.TipoViolenciaRepository;

import jakarta.transaction.Transactional;
@Service
public class TipoViolenciaServiceImp implements TipoViolenciaService {

	@Autowired
	private TipoViolenciaRepository tipoRep;
	@Override
	@Transactional
	public List<Tipo_Violencia> listarTodos() {
		// TODO Auto-generated method stub
		return tipoRep.findAll();
	}

	@Override
	public Tipo_Violencia buscarPorId(Long id) throws EntityNotFoundException{
		Optional<Tipo_Violencia> tipovio = tipoRep.findById(id);
		if (tipovio.isEmpty())
			throw new EntityNotFoundException(EntityNotFoundExceptionMessages.TIPO_VIOLENCIA_NOT_FOUND);
		return tipovio.get();
	}

	@Override
	@Transactional
	public Tipo_Violencia grabar(Tipo_Violencia tipoViolencia) throws IllegalOperationException {
		if (!tipoRep.findByDescripcion(tipoViolencia.getDescripcion()).isEmpty()) {
			throw new IllegalOperationException("Ya existe un tipo de violencia registrada con este nombre.");
		}
		return tipoRep.save(tipoViolencia);
	}

	@Override
	public Tipo_Violencia actualizar(Tipo_Violencia tipoViolencia, Long id)
			throws EntityNotFoundException, IllegalOperationException {
		Optional<Tipo_Violencia> tViolencia=tipoRep.findById(id);
		if(!tViolencia.isPresent())
			throw new EntityNotFoundException(EntityNotFoundExceptionMessages.TIPO_VIOLENCIA_NOT_FOUND);
		Optional<Tipo_Violencia> existeDescripcion=tipoRep.findByDescripcion(tipoViolencia.getDescripcion());
		if(existeDescripcion.isPresent()&&!existeDescripcion.get().getIdtipoviolencia().equals(id)
				&& !tipoViolencia.getDescripcion().equalsIgnoreCase(tViolencia.get().getDescripcion())) {
			throw new IllegalOperationException("Ya existe una entidad registrada con este nombre.");
		}
		
		tipoViolencia.setIdtipoviolencia(id);
		return tipoRep.save(tipoViolencia) ;
	}

	@Override
	@Transactional
	public void eliminar(Long id) throws EntityNotFoundException, IllegalOperationException {
		// TODO Auto-generated method stub
		tipoRep.deleteById(id);
	}

	@Override
	@Transactional
	public Tipo_Violencia buscarPorDescripcion(String descripcion) {
		Optional<Tipo_Violencia> tVioReg = tipoRep.findByDescripcion(descripcion);
		if (tVioReg.isEmpty())
			throw new EntityNotFoundException(EntityNotFoundExceptionMessages.TIPO_VIOLENCIA_NOT_FOUND);
		return tVioReg.get();
	}

}
