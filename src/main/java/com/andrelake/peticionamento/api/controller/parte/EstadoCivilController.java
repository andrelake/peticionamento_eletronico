package com.andrelake.peticionamento.api.controller.parte;

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

import com.andrelake.peticionamento.domain.model.parte.EstadoCivil;
import com.andrelake.peticionamento.domain.service.parte.EstadoCivilService;

@RestController
@RequestMapping("/estadoscivil")
public class EstadoCivilController {

	@Autowired
	private EstadoCivilService service;
	
	@GetMapping
	public ResponseEntity<List<EstadoCivil>> findAll(){
		
		List<EstadoCivil> list = service.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EstadoCivil> findById(@PathVariable Long id) {
		
		EstadoCivil estadoCivil = service.findById(id);
		return ResponseEntity.ok(estadoCivil);
	}
	
	@PostMapping
	public ResponseEntity<EstadoCivil> insert(@RequestBody EstadoCivil estadoCivil) {
		
		estadoCivil = service.save(estadoCivil);
		return ResponseEntity.status(HttpStatus.CREATED).body(estadoCivil);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EstadoCivil> update(@PathVariable Long id, @RequestBody EstadoCivil estadoCivil) {
		
		EstadoCivil estadoCivilRoot = service.findById(id);
		
		BeanUtils.copyProperties(estadoCivil, estadoCivilRoot, "id");
		
		service.save(estadoCivilRoot);
		return ResponseEntity.ok(estadoCivilRoot);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remove(@PathVariable Long id) {
		
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
