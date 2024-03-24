package com.anfic.rest.APP_ProyDesarrolloWeb.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anfic.rest.APP_ProyDesarrolloWeb.domain.Entidad_Registro;
import com.anfic.rest.APP_ProyDesarrolloWeb.dto.EntidadRegistroDTO;
import com.anfic.rest.APP_ProyDesarrolloWeb.exception.EntityNotFoundException;
import com.anfic.rest.APP_ProyDesarrolloWeb.exception.IllegalOperationException;
import com.anfic.rest.APP_ProyDesarrolloWeb.service.EntidadRegistroService;
import com.anfic.rest.APP_ProyDesarrolloWeb.util.ApiResponse;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "/api/v1/entidadregistro")
public class EntidadRegistroController {
	@Autowired
	private EntidadRegistroService entidadService;
	@Autowired
	private ModelMapper modelMapper;
	@GetMapping
	public ResponseEntity<?> obtenerTodos(){
		List<Entidad_Registro> entidad=entidadService.listarTodos();
		if(entidad==null || entidad.isEmpty()) {
			return ResponseEntity.noContent().build();
		}else {
			List<EntidadRegistroDTO> entidadDTO=entidad.stream()
					.map(entidad_registro -> modelMapper.map(entidad_registro, EntidadRegistroDTO.class))
					.collect(Collectors.toList());
			ApiResponse<List<EntidadRegistroDTO>> response=new ApiResponse<>(true, "Lista de entidades obtenidad con exito", entidadDTO);
			return ResponseEntity.ok(response);
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<?>ObtenerPorId(@PathVariable Long id){
		Entidad_Registro entidad=entidadService.buscarPorId(id);
		EntidadRegistroDTO entidadDTO=modelMapper.map(entidad, EntidadRegistroDTO.class);
		ApiResponse<EntidadRegistroDTO>response=new ApiResponse<EntidadRegistroDTO>(true, "Datos de la entidad obtenidos con éxito", entidadDTO);
		return ResponseEntity.ok(response);
	}
	@PostMapping
	public ResponseEntity<?> guardar(@Valid @RequestBody EntidadRegistroDTO entidadDTO)throws IllegalOperationException{
		Entidad_Registro entidad=modelMapper.map(entidadDTO, Entidad_Registro.class);
		entidadService.grabar(entidad);
		EntidadRegistroDTO saveEntidadDTO=modelMapper.map(entidad, EntidadRegistroDTO.class);
		ApiResponse<EntidadRegistroDTO> response=new ApiResponse<>(true, "Datos del Investigador grabados con éxito.", saveEntidadDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizar(@Valid @RequestBody EntidadRegistroDTO entidadDTO, @PathVariable Long id) throws EntityNotFoundException, IllegalOperationException {       
	  	
    	Entidad_Registro entidad = modelMapper.map(entidadDTO, Entidad_Registro.class);
    	entidadService.actualizar(entidad, id);
    	EntidadRegistroDTO updateEntidadDTO = modelMapper.map(entidad, EntidadRegistroDTO.class);
    	ApiResponse<EntidadRegistroDTO> response = new ApiResponse<>(true, "Datos de la entidad actualizados con éxito", updateEntidadDTO);
    	return ResponseEntity.status(HttpStatus.OK).body(response);
    }
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id) throws EntityNotFoundException, IllegalOperationException {
		entidadService.eliminar(id);
    	ApiResponse<?> response = new ApiResponse<>(true, "Entidad eliminada con éxito", null);
    	return ResponseEntity.status(HttpStatus.OK).body(response);//NO_CONTENT
    } 
}
