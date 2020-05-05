package com.andrelake.peticionamento.api.controller.peticao;

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

import com.andrelake.peticionamento.domain.model.peticao.ClasseProcesso;
import com.andrelake.peticionamento.domain.service.peticao.ClasseProcessoService;

@RestController
@RequestMapping("/classes")
public class ClasseProcessoController {

	@Autowired
	private ClasseProcessoService classeService;
	
	@GetMapping
	public ResponseEntity<List<ClasseProcesso>> findAll() {
		
		List<ClasseProcesso> list = classeService.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClasseProcesso> findById(@PathVariable Long id) {
		
		ClasseProcesso classe = classeService.findById(id);
		return ResponseEntity.ok(classe);
	}
	
	@PostMapping
	public ResponseEntity<ClasseProcesso> insert(@RequestBody @Valid ClasseProcesso classe) {
		
		classe = classeService.save(classe);
		return ResponseEntity.status(HttpStatus.CREATED).body(classe);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ClasseProcesso> update(@PathVariable Long id, @RequestBody @Valid ClasseProcesso classe) {
		
		ClasseProcesso oldClass = classeService.findById(id);
		
		BeanUtils.copyProperties(classe, oldClass, "id", "competencia");
		
		oldClass = classeService.save(oldClass);
		return ResponseEntity.ok(oldClass);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remove(@PathVariable Long id) {
		
		classeService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/nomesearch")
	public ResponseEntity<List<ClasseProcesso>> findByNome(@RequestParam String text) {
		
		List<ClasseProcesso> list = classeService.findByNome(text);
		return ResponseEntity.ok(list);
	}
}
