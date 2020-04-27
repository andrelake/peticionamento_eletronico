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

import com.andrelake.peticionamento.domain.model.AssuntoPrincipal;
import com.andrelake.peticionamento.domain.service.AssuntoPrincipalService;

@RestController
@RequestMapping("/assuntos")
public class AssuntoPrincipalController {

	@Autowired
	private AssuntoPrincipalService assuntoService;
	
	@GetMapping
	public ResponseEntity<List<AssuntoPrincipal>> findAll() {
		
		List<AssuntoPrincipal> list = assuntoService.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AssuntoPrincipal> findById(@PathVariable Long id) {
		
		AssuntoPrincipal assunto = assuntoService.findById(id);
		return ResponseEntity.ok(assunto);
	}
	
	@PostMapping
	public ResponseEntity<AssuntoPrincipal> insert(@RequestBody AssuntoPrincipal assunto) {
		
		assunto = assuntoService.save(assunto);
		return ResponseEntity.status(HttpStatus.CREATED).body(assunto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AssuntoPrincipal> update(@PathVariable Long id, @RequestBody AssuntoPrincipal assunto) {
		
		AssuntoPrincipal oldAssunto = assuntoService.findById(id);
		
		BeanUtils.copyProperties(assunto, oldAssunto, "id", "classe", "competencia");
		
		oldAssunto = assuntoService.save(oldAssunto);
		return ResponseEntity.ok(oldAssunto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remove(@PathVariable Long id) {
		
		assuntoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/nomesearch")
	public ResponseEntity<List<AssuntoPrincipal>> findByNome(@RequestParam String text) {
		
		List<AssuntoPrincipal> list = assuntoService.findByNome(text);
		return ResponseEntity.ok(list);
	}
}
