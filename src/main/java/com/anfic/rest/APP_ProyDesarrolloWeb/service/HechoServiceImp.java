package com.anfic.rest.APP_ProyDesarrolloWeb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anfic.rest.APP_ProyDesarrolloWeb.domain.Entidad_Registro;
import com.anfic.rest.APP_ProyDesarrolloWeb.domain.Hecho;
import com.anfic.rest.APP_ProyDesarrolloWeb.domain.Tipo_Violencia;
import com.anfic.rest.APP_ProyDesarrolloWeb.exception.EntityNotFoundException;
import com.anfic.rest.APP_ProyDesarrolloWeb.exception.EntityNotFoundExceptionMessages;
import com.anfic.rest.APP_ProyDesarrolloWeb.exception.IllegalOperationException;
import com.anfic.rest.APP_ProyDesarrolloWeb.repository.EntidadRegistroRepository;
import com.anfic.rest.APP_ProyDesarrolloWeb.repository.HechoRepository;
import com.anfic.rest.APP_ProyDesarrolloWeb.repository.TipoViolenciaRepository;

import jakarta.transaction.Transactional;
@Service
public class HechoServiceImp implements HechoService {
	@Autowired
	private HechoRepository hRepository;
	@Autowired
	private TipoViolenciaRepository tipoVioRep;
	@Autowired
	private EntidadRegistroRepository entidadRep;

	@Override
	@Transactional
	public List<Hecho> listarTodos() {
		// TODO Auto-generated method stub
		return hRepository.findAll();
	}

	@Override
	public Hecho buscarPorId(Long id) throws EntityNotFoundException {
		Optional<Hecho> hecho = hRepository.findById(id);
		if (hecho.isEmpty())
			throw new EntityNotFoundException(EntityNotFoundExceptionMessages.HECHO_NOT_FOUND);
		return hecho.get();
	}

	@Override
	@Transactional
	public Hecho grabar(Hecho hecho) throws IllegalOperationException {
		return hRepository.save(hecho);
	}

	@Override
	public Hecho actualizar(Hecho hecho, Long id) throws EntityNotFoundException, IllegalOperationException {
		Optional<Hecho> hEntity = hRepository.findById(id);
		if (!hEntity.isPresent())
			throw new EntityNotFoundException(EntityNotFoundExceptionMessages.HECHO_NOT_FOUND);
		hecho.setIdhecho(id);
		return hRepository.save(hecho);
	}

	@Override
	@Transactional
	public void eliminar(Long i) throws EntityNotFoundException, IllegalOperationException {
		hRepository.findById(i)
				.orElseThrow(() -> new EntityNotFoundException(EntityNotFoundExceptionMessages.HECHO_NOT_FOUND));
		hRepository.deleteById(i);

	}

	@Override
	@Transactional
	public Hecho asignarViolencia(Long idhecho, Long idtipo) throws EntityNotFoundException, IllegalOperationException {
		Hecho pryEntity = hRepository.findById(idhecho)
				.orElseThrow(() -> new EntityNotFoundException(EntityNotFoundExceptionMessages.HECHO_NOT_FOUND));

		Tipo_Violencia tipoEntity = tipoVioRep.findById(idtipo).orElseThrow(
				() -> new EntityNotFoundException(EntityNotFoundExceptionMessages.TIPO_VIOLENCIA_NOT_FOUND));
		if (pryEntity.getTipovio() != null) {
			throw new IllegalOperationException("El tipo de violencia ya se asignó al proyecto");
		}
		pryEntity.setTipovio(tipoEntity);
		return hRepository.save(pryEntity);
	}

	@Override
	public Hecho asignarEntidad(Long idhecho, Long identidad)
			throws EntityNotFoundException, IllegalOperationException {
		Hecho pryEntity = hRepository.findById(idhecho)
				.orElseThrow(() -> new EntityNotFoundException(EntityNotFoundExceptionMessages.HECHO_NOT_FOUND));

		Entidad_Registro entidadEntity = entidadRep.findById(identidad).orElseThrow(
				() -> new EntityNotFoundException(EntityNotFoundExceptionMessages.ETIDAD_REGISTRO_NOT_FOUND));
		if (pryEntity.getEntidad() != null) {
			throw new IllegalOperationException("La entidad de registro ya se asignó al proyecto");
		}
		pryEntity.setEntidad(entidadEntity);
		return hRepository.save(pryEntity);
	}

}
