package com.andrelake.peticionamento.api.controller.parte;

import java.util.List;

import javax.validation.Valid;

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

import com.andrelake.peticionamento.domain.model.parte.Nacionalidade;
import com.andrelake.peticionamento.domain.service.parte.NacionalidadeService;

@RestController
@RequestMapping("/nacionalidades")
public class NacionalidadeController {

	@Autowired
	private NacionalidadeService service;
	
	@GetMapping
	public ResponseEntity<List<Nacionalidade>> findAll(){
		
		List<Nacionalidade> list = service.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Nacionalidade> findById(@PathVariable Long id) {
		
		Nacionalidade nacionalidade = service.findById(id);
		return ResponseEntity.ok(nacionalidade);
	}
	
	@PostMapping
	public ResponseEntity<Nacionalidade> insert(@RequestBody @Valid Nacionalidade nacionalidade) {
		
		nacionalidade = service.save(nacionalidade);
		return ResponseEntity.status(HttpStatus.CREATED).body(nacionalidade);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Nacionalidade> update(@PathVariable Long id, @RequestBody @Valid Nacionalidade nacionalidade) {
		
		Nacionalidade nacionalidadeRoot = service.findById(id);
		
		BeanUtils.copyProperties(nacionalidade, nacionalidadeRoot, "id");
		
		service.save(nacionalidadeRoot);
		return ResponseEntity.ok(nacionalidadeRoot);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remove(@PathVariable Long id) {
		
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/nomesearch")
	public ResponseEntity<List<Nacionalidade>> findByNome(@RequestParam String text) {
		
		List<Nacionalidade> list = service.findByNome(text);
		return ResponseEntity.ok(list);
	}
}
