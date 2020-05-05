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

import com.andrelake.peticionamento.domain.model.parte.Profissao;
import com.andrelake.peticionamento.domain.service.parte.ProfissaoService;

@RestController
@RequestMapping("/profissoes")
public class ProfissaoController {

	@Autowired
	private ProfissaoService service;
	
	@GetMapping
	public ResponseEntity<List<Profissao>> findAll(){
		
		List<Profissao> list = service.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Profissao> findById(@PathVariable Long id) {
		
		Profissao profissao = service.findById(id);
		return ResponseEntity.ok(profissao);
	}
	
	@PostMapping
	public ResponseEntity<Profissao> insert(@RequestBody @Valid Profissao profissao) {
		
		profissao = service.save(profissao);
		return ResponseEntity.status(HttpStatus.CREATED).body(profissao);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Profissao> update(@PathVariable Long id, @RequestBody @Valid Profissao profissao) {
		
		Profissao profissaoRoot = service.findById(id);
		
		BeanUtils.copyProperties(profissao, profissaoRoot, "id");
		
		service.save(profissaoRoot);
		return ResponseEntity.ok(profissaoRoot);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remove(@PathVariable Long id) {
		
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/nomesearch")
	public ResponseEntity<List<Profissao>> findByNome(@RequestParam String text) {
		
		List<Profissao> list = service.findByNome(text);
		return ResponseEntity.ok(list);
	}
}
