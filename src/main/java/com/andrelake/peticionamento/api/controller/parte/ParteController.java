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

import com.andrelake.peticionamento.domain.model.parte.Parte;
import com.andrelake.peticionamento.domain.service.parte.ParteService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/partes")
public class ParteController {

	@Autowired
	private ParteService parteService;
	
	@GetMapping
	public ResponseEntity<List<Parte>> findAll() {
		
		List<Parte> list = parteService.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Parte> findById(@PathVariable Long id) {
		
		Parte parte = parteService.findById(id);
		return ResponseEntity.ok(parte);
	}
	
	@PostMapping
	public ResponseEntity<Parte> insert(@RequestBody Parte parte) {
		
		parte = parteService.save(parte);
		return ResponseEntity.status(HttpStatus.CREATED).body(parte);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Parte> update(@PathVariable Long id, @RequestBody Parte parte) {
		
		Parte oldParte = parteService.findById(id);
		
		BeanUtils.copyProperties(parte, oldParte, "id", "estadoCivil", "nacionalidade", "participacao", "profissao");
		
		oldParte = parteService.save(oldParte);
		
		return ResponseEntity.ok(oldParte);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Parte> partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> campos, HttpServletRequest request) {
		
		Parte parteAtual = parteService.findById(id);
		
		merge(campos, parteAtual, request);
		
		return update(id, parteAtual);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remove(@PathVariable Long id) {
		
		parteService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	//utils
	private void merge(Map<String, Object> dadosOrigem, Parte parteDestino, HttpServletRequest request) {
		
		ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);
		
		try {
			ObjectMapper objMapper = new ObjectMapper();
			objMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			
			Parte parteOrigem = objMapper.convertValue(dadosOrigem, Parte.class);
			
			dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
				
				Field field = ReflectionUtils.findField(Parte.class, nomePropriedade);
				field.setAccessible(true);
				
				Object novoValor = ReflectionUtils.getField(field, parteOrigem);
				
				ReflectionUtils.setField(field, parteDestino, novoValor);
			});
		}
		catch(IllegalArgumentException e) {
			throw new HttpMessageNotReadableException(e.getMessage(), serverHttpRequest);
		}
	}
}
