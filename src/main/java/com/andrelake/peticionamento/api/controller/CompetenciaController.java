package com.andrelake.peticionamento.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
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

import com.andrelake.peticionamento.domain.model.Competencia;
import com.andrelake.peticionamento.domain.service.CompetenciaService;

@RestController
@RequestMapping("/competencias")
public class CompetenciaController {

	@Autowired
	private CompetenciaService service;
	
	@GetMapping
	public ResponseEntity<List<Competencia>> findAll() {
		
		List<Competencia> list = service.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Competencia> findById(@PathVariable Long id) {
		
		Competencia comp = service.findById(id);
		return ResponseEntity.ok(comp);
	}
	
	@PostMapping
	public ResponseEntity<Competencia> insert(@RequestBody Competencia competencia) {
		
		competencia = service.save(competencia);
		return ResponseEntity.status(HttpStatus.CREATED).body(competencia);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Competencia> update(@PathVariable Long id, @RequestBody Competencia competencia) {
		
		Competencia comp = service.findById(id);
		
		BeanUtils.copyProperties(competencia, comp, "id");
		
		comp = service.save(comp);
		
		return ResponseEntity.ok(comp);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remove(@PathVariable Long id) {
		
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
