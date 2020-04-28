package com.andrelake.peticionamento.api.controller.peticao;

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

import com.andrelake.peticionamento.domain.model.peticao.PeticaoInicialPrimeiroGrau;
import com.andrelake.peticionamento.domain.service.peticao.PeticaoInicialService;

@RestController
@RequestMapping("/petiniciais")
public class PeticaoInicialController {

	@Autowired
	private PeticaoInicialService petService;
	
	@GetMapping
	public ResponseEntity<List<PeticaoInicialPrimeiroGrau>> findAll() {
		
		List<PeticaoInicialPrimeiroGrau> list = petService.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PeticaoInicialPrimeiroGrau> findById(@PathVariable Long id) {
		
		PeticaoInicialPrimeiroGrau pet = petService.findById(id);
		return ResponseEntity.ok(pet);
	}
	
	@PostMapping
	public ResponseEntity<PeticaoInicialPrimeiroGrau> insert(@RequestBody PeticaoInicialPrimeiroGrau peticao) {
		
		peticao = petService.save(peticao);
		return ResponseEntity.status(HttpStatus.CREATED).body(peticao);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PeticaoInicialPrimeiroGrau> update(@PathVariable Long id, @RequestBody PeticaoInicialPrimeiroGrau peticao) {
		
		PeticaoInicialPrimeiroGrau oldPet = petService.findById(id);
		
		BeanUtils.copyProperties(peticao, oldPet, "id", "assunto", "classe", "competencia");
		
		oldPet = petService.save(oldPet);
		return ResponseEntity.ok(oldPet);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remove(@PathVariable Long id) {
		
		petService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
