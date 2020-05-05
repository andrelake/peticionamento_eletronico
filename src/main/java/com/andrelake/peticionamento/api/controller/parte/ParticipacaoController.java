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
import org.springframework.web.bind.annotation.RestController;

import com.andrelake.peticionamento.domain.model.parte.Participacao;
import com.andrelake.peticionamento.domain.service.parte.ParticipacaoService;

@RestController
@RequestMapping("/participacoes")
public class ParticipacaoController {

	@Autowired
	private ParticipacaoService service;
	
	@GetMapping
	public ResponseEntity<List<Participacao>> findAll(){
		
		List<Participacao> list = service.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Participacao> findById(@PathVariable Long id) {
		
		Participacao participacao = service.findById(id);
		return ResponseEntity.ok(participacao);
	}
	
	@PostMapping
	public ResponseEntity<Participacao> insert(@RequestBody @Valid Participacao participacao) {
		
		participacao = service.save(participacao);
		return ResponseEntity.status(HttpStatus.CREATED).body(participacao);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Participacao> update(@PathVariable Long id, @RequestBody @Valid Participacao participacao) {
		
		Participacao participacaoRoot = service.findById(id);
		
		BeanUtils.copyProperties(participacao, participacaoRoot, "id");
		
		service.save(participacaoRoot);
		return ResponseEntity.ok(participacaoRoot);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remove(@PathVariable Long id) {
		
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
