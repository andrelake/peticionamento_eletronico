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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.andrelake.peticionamento.domain.model.Foro;
import com.andrelake.peticionamento.domain.service.ForoService;

@RestController
@RequestMapping("/foros")
public class ForoController {

	@Autowired
	private ForoService service;
	
	@GetMapping
	public ResponseEntity<List<Foro>> findAll(){
		
		List<Foro> list = service.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Foro> findById(@PathVariable Long id) {
		
		Foro foro = service.findById(id);
		return ResponseEntity.ok(foro);
	}
	
	@PostMapping
	public ResponseEntity<Foro> insert(@RequestBody Foro foro) {
		
		foro = service.save(foro);
		return ResponseEntity.status(HttpStatus.CREATED).body(foro);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Foro> update(@PathVariable Long id, @RequestBody Foro foro) {
		
		Foro foroRoot = service.findById(id);
		
		BeanUtils.copyProperties(foro, foroRoot, "id");
		
		service.save(foroRoot);
		return ResponseEntity.ok(foroRoot);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remove(@PathVariable Long id) {
		
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/nomesearch")
	public ResponseEntity<List<Foro>> findByNome(@RequestParam String text) {
		
		List<Foro> list = service.findByNome(text);
		return ResponseEntity.ok(list);
	}
}
