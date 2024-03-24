package com.anfic.rest.APP_ProyDesarrolloWeb.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anfic.rest.APP_ProyDesarrolloWeb.domain.Hecho;
import com.anfic.rest.APP_ProyDesarrolloWeb.dto.HechoDTO;
import com.anfic.rest.APP_ProyDesarrolloWeb.exception.EntityNotFoundException;
import com.anfic.rest.APP_ProyDesarrolloWeb.exception.IllegalOperationException;
import com.anfic.rest.APP_ProyDesarrolloWeb.service.HechoService;
import com.anfic.rest.APP_ProyDesarrolloWeb.util.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/hecho")
public class HechoController {
	@Autowired
	private HechoService hservice;
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public ResponseEntity<?> obtenerTodos() {
		List<Hecho> hechos = hservice.listarTodos();
		if (hechos == null || hechos.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			List<HechoDTO> hechoDTO = hechos.stream().map(hecho -> modelMapper.map(hecho, HechoDTO.class))
					.collect(Collectors.toList());
			ApiResponse<List<HechoDTO>> response = new ApiResponse<>(true,
					"Lista de hechos de violencia obtenida con éxito", hechoDTO);
			return ResponseEntity.ok(response);
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
		Hecho hechos = hservice.buscarPorId(id);
		HechoDTO proyectoDTO = modelMapper.map(hechos, HechoDTO.class);
		ApiResponse<HechoDTO> response = new ApiResponse<>(true, "Datos del Proyecto obtenidos con éxito", proyectoDTO);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<?> guardar(@Valid @RequestBody HechoDTO hechoDTO)
			throws com.anfic.rest.APP_ProyDesarrolloWeb.exception.IllegalOperationException {
		Hecho hechos = modelMapper.map(hechoDTO, Hecho.class);
		hservice.grabar(hechos);
		HechoDTO savedHechoDTO = modelMapper.map(hechos, HechoDTO.class);
		ApiResponse<HechoDTO> response = new ApiResponse<>(true,
				"Datos del Hecho grabados con éxito", savedHechoDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	@PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody HechoDTO hechoDTO, @PathVariable Long id) throws EntityNotFoundException, IllegalOperationException {       
  	
    	Hecho hechos = modelMapper.map(hechoDTO, Hecho.class);
    	hservice.actualizar(hechos, id);
    	HechoDTO updatedHechoDTO = modelMapper.map(hechos, HechoDTO.class);
    	ApiResponse<HechoDTO> response = new ApiResponse<>(true, "Datos del hecho actualizados con éxito", updatedHechoDTO);
    	return ResponseEntity.status(HttpStatus.OK).body(response);
    }
	@DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) throws EntityNotFoundException, IllegalOperationException {
    	hservice.eliminar(id);
    	ApiResponse<?> response = new ApiResponse<>(true, "hecho eliminado con éxito", null);
    	return ResponseEntity.status(HttpStatus.OK).body(response);//NO_CONTENT
    } 
	@PutMapping(value = "/{idhecho}/violencia/{idtipo}")
    public ResponseEntity<?> asignarViolencia (@PathVariable Long idhecho, @PathVariable Long idtipo) throws EntityNotFoundException, IllegalOperationException{
    	Hecho hechos = hservice.asignarViolencia(idhecho, idtipo);
    	HechoDTO hechoDTO = modelMapper.map(hechos, HechoDTO.class);
    	ApiResponse<?> response = new ApiResponse<>(true, "Violencia Asignado al Hecho con éxito", hechoDTO);
    	return ResponseEntity.status(HttpStatus.OK).body(response);
    }
	@PutMapping(value = "/{idHecho}/entidad/{identidad}")
    public ResponseEntity<?> asignarEntidad (@PathVariable Long idHecho, @PathVariable Long identidad) throws EntityNotFoundException, IllegalOperationException{
    	Hecho hechos = hservice.asignarEntidad(idHecho, identidad);
    	HechoDTO hechoDTO = modelMapper.map(hechos, HechoDTO.class);
    	ApiResponse<?> response = new ApiResponse<>(true, "Entidad Asignado al Hecho con éxito", hechoDTO);
    	return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
