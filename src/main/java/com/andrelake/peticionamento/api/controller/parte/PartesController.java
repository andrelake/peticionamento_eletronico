package com.andrelake.peticionamento.api.controller.parte;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andrelake.peticionamento.domain.model.parte.Partes;
import com.andrelake.peticionamento.domain.service.parte.PartesService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/partesss")
public class PartesController {

	@Autowired
	private PartesService partesService;
	
	@GetMapping
	public ResponseEntity<List<Partes>> findAll() {
		
		List<Partes> list = partesService.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Partes> findById(@PathVariable Long id) {
		
		Partes partes = partesService.findById(id);
		return ResponseEntity.ok(partes);
	}
	
	@PostMapping
	public ResponseEntity<Partes> insert(@RequestBody Partes partes) {
		
		partes = partesService.save(partes);
		return ResponseEntity.status(HttpStatus.CREATED).body(partes);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Partes> update(@PathVariable Long id, @RequestBody Partes partes) {
		
		Partes oldPartes = partesService.findById(id);
		
		BeanUtils.copyProperties(partes, oldPartes, "id");
		
		oldPartes = partesService.save(oldPartes);
		return ResponseEntity.ok(oldPartes);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Partes> partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> campos, HttpServletRequest request) {
		
		Partes partes = partesService.findById(id);
		
		merge(campos, partes, request);
		
		return update(id, partes);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remove(@PathVariable Long id) {
		
		partesService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	//utils
	public void merge(Map<String, Object> dadosOrigem, Partes partesDestino, HttpServletRequest request) {
		
		ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);
		
		try {
			ObjectMapper objMapper = new ObjectMapper();
			objMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			
			Partes partesOrigem = objMapper.convertValue(dadosOrigem, Partes.class);
			
			dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
				
				Field field = ReflectionUtils.findField(Partes.class, nomePropriedade);
				field.setAccessible(true);
				
				Object novoValor = ReflectionUtils.getField(field, partesOrigem);
				
				ReflectionUtils.setField(field, partesDestino, novoValor);
			});
		}
		catch(IllegalArgumentException e) {
			throw new HttpMessageNotReadableException(e.getMessage(), serverHttpRequest);
		}
	}
}
