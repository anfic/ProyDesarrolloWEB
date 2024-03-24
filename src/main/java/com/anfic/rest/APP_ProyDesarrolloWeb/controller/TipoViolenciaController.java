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

import com.anfic.rest.APP_ProyDesarrolloWeb.domain.Tipo_Violencia;
import com.anfic.rest.APP_ProyDesarrolloWeb.dto.TipoViolenciaDTO;
import com.anfic.rest.APP_ProyDesarrolloWeb.exception.EntityNotFoundException;
import com.anfic.rest.APP_ProyDesarrolloWeb.exception.IllegalOperationException;
import com.anfic.rest.APP_ProyDesarrolloWeb.service.TipoViolenciaService;
import com.anfic.rest.APP_ProyDesarrolloWeb.util.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/tipoviolencia")
public class TipoViolenciaController {
	@Autowired
	private TipoViolenciaService tipoViolencia;
	@Autowired
	private ModelMapper modelMapper;
	@GetMapping
	public ResponseEntity<?> obtenerTodos(){
		List<Tipo_Violencia> tVio=tipoViolencia.listarTodos();
		if(tVio==null || tVio.isEmpty()) {
			return ResponseEntity.noContent().build();
		}else {
			List<TipoViolenciaDTO> tipoDTO=tVio.stream()
					.map(Tipo_Violencia -> modelMapper.map(Tipo_Violencia, TipoViolenciaDTO.class))
					.collect(Collectors.toList());
			ApiResponse<List<TipoViolenciaDTO>> response=new ApiResponse<>(true, "Lista de tipos de violencia obtenidad con exito", tipoDTO);
			return ResponseEntity.ok(response);
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<?>ObtenerPorId(@PathVariable Long id){
		Tipo_Violencia tipo=tipoViolencia.buscarPorId(id);
		TipoViolenciaDTO tipodDTO=modelMapper.map(tipo, TipoViolenciaDTO.class);
		ApiResponse<TipoViolenciaDTO>response=new ApiResponse<TipoViolenciaDTO>(true, "Datos del tipo de violencia obtenidos con éxito", tipodDTO);
		return ResponseEntity.ok(response);
	}
	@PostMapping
	public ResponseEntity<?> guardar(@Valid @RequestBody TipoViolenciaDTO tipoDTO)throws IllegalOperationException{
		Tipo_Violencia tipo=modelMapper.map(tipoDTO, Tipo_Violencia.class);
		tipoViolencia.grabar(tipo);
		TipoViolenciaDTO saveTipodDTO=modelMapper.map(tipo, TipoViolenciaDTO.class);
		ApiResponse<TipoViolenciaDTO> response=new ApiResponse<>(true, "Datos de tipo de violencia grabados con éxito.", saveTipodDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizar(@Valid @RequestBody TipoViolenciaDTO tipodDTO, @PathVariable Long id) throws EntityNotFoundException, IllegalOperationException {       
	  	
    	Tipo_Violencia entidad = modelMapper.map(tipodDTO, Tipo_Violencia.class);
    	tipoViolencia.actualizar(entidad, id);
    	TipoViolenciaDTO updateTipodDTO = modelMapper.map(entidad, TipoViolenciaDTO.class);
    	ApiResponse<TipoViolenciaDTO> response = new ApiResponse<>(true, "Datos del tipo de violencia actualizados con éxito", updateTipodDTO);
    	return ResponseEntity.status(HttpStatus.OK).body(response);
    }
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id) throws EntityNotFoundException, IllegalOperationException {
		tipoViolencia.eliminar(id);
    	ApiResponse<?> response = new ApiResponse<>(true, "Tipo de Violencia eliminada con éxito", null);
    	return ResponseEntity.status(HttpStatus.OK).body(response);//NO_CONTENT
    } 

}
