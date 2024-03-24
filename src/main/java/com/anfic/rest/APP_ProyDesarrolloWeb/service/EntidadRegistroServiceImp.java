package com.anfic.rest.APP_ProyDesarrolloWeb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anfic.rest.APP_ProyDesarrolloWeb.domain.Entidad_Registro;
import com.anfic.rest.APP_ProyDesarrolloWeb.exception.EntityNotFoundException;
import com.anfic.rest.APP_ProyDesarrolloWeb.exception.EntityNotFoundExceptionMessages;
import com.anfic.rest.APP_ProyDesarrolloWeb.exception.IllegalOperationException;
import com.anfic.rest.APP_ProyDesarrolloWeb.repository.EntidadRegistroRepository;

import jakarta.transaction.Transactional;

@Service
public class EntidadRegistroServiceImp implements EntidadRegistroService {
	@Autowired
	private EntidadRegistroRepository entidadRep;
	

	@Transactional
	public List<Entidad_Registro> listarTodos() {
		// TODO Auto-generated method stub
		return entidadRep.findAll();
	}

	@Transactional
	public Entidad_Registro buscarPorId(Long id) throws EntityNotFoundException {
		Optional<Entidad_Registro> entireg = entidadRep.findById(id);
		if (entireg.isEmpty())
			throw new EntityNotFoundException(EntityNotFoundExceptionMessages.ETIDAD_REGISTRO_NOT_FOUND);
		return entireg.get();
	}

	@Transactional
	public Entidad_Registro grabar(Entidad_Registro entidadRegistro) throws IllegalOperationException {
		if (!entidadRep.findByDescripcion(entidadRegistro.getDescripcion()).isEmpty()) {
			throw new IllegalOperationException("Ya existe una entidad registrada con este nombre.");
		}
		if (!entidadRep.findBySiglas(entidadRegistro.getSiglas()).isEmpty()) {
			throw new IllegalOperationException("Ya existe una entidad registrada con estas siglas.");
		}
		return entidadRep.save(entidadRegistro);
	}

	@Transactional
	public Entidad_Registro actualizar(Entidad_Registro entidadRegistro, Long id)throws EntityNotFoundException,IllegalOperationException {
		Optional<Entidad_Registro> entidad=entidadRep.findById(id);
		if(!entidad.isPresent())
			throw new EntityNotFoundException(EntityNotFoundExceptionMessages.ETIDAD_REGISTRO_NOT_FOUND);
		Optional<Entidad_Registro> existeDescripcion=entidadRep.findByDescripcion(entidadRegistro.getDescripcion());
		if(existeDescripcion.isPresent()&&!existeDescripcion.get().getIdentidad().equals(id)
				&& !entidadRegistro.getDescripcion().equalsIgnoreCase(entidad.get().getDescripcion())) {
			throw new IllegalOperationException("Ya existe una entidad registrada con este nombre.");
		}
		Optional<Entidad_Registro> existeSigla=entidadRep.findBySiglas(entidadRegistro.getSiglas());
		if(existeSigla.isPresent()&&!existeSigla.get().getIdentidad().equals(id)
				&& !entidadRegistro.getSiglas().equals(entidad.get().getSiglas())) {
			throw new IllegalOperationException("Ya existe una entidad registrada con esta sigla.");
		}
		entidadRegistro.setIdentidad(id);
		return entidadRep.save(entidadRegistro) ;
	}

	@Transactional
	public void eliminar(Long id) throws EntityNotFoundException, IllegalOperationException {
		//entidad_registro entidad = entidadRep.findById(id).orElseThrow(() -> new EntityNotFoundException(EntityNotFoundExceptionMessages.ETIDAD_REGISTRO_NOT_FOUND));
		entidadRep.deleteById(id);
	}

	@Override
	@Transactional
	public Entidad_Registro buscarPorDescripcion(String descripcion) {
		Optional<Entidad_Registro> entiReg = entidadRep.findByDescripcion(descripcion);
		if (entiReg.isEmpty())
			throw new EntityNotFoundException(EntityNotFoundExceptionMessages.ETIDAD_REGISTRO_NOT_FOUND);
		return entiReg.get();
	}

	@Override
	public Entidad_Registro buscarPorSiglas(String siglas) {
		Optional<Entidad_Registro> entiReg = entidadRep.findBySiglas(siglas);
		if (entiReg.isEmpty())
			throw new EntityNotFoundException(EntityNotFoundExceptionMessages.ETIDAD_REGISTRO_NOT_FOUND);
		return entiReg.get();
	}

	

}
