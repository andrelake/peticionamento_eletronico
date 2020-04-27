package com.andrelake.peticionamento.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.andrelake.peticionamento.domain.exception.AssuntoPrincipalEmUsoException;
import com.andrelake.peticionamento.domain.exception.AssuntoPrincipalNaoEncontradoException;
import com.andrelake.peticionamento.domain.model.AssuntoPrincipal;
import com.andrelake.peticionamento.domain.model.ClasseProcesso;
import com.andrelake.peticionamento.domain.repository.AssuntoPrincipalRepository;

@Service
public class AssuntoPrincipalService {

	@Autowired
	private ClasseProcessoService classeService;
	
	@Autowired
	private AssuntoPrincipalRepository assuntoPrincipalRepository;
	
	public List<AssuntoPrincipal> findAll(){
		
		return assuntoPrincipalRepository.findAll();
	}
	
	public AssuntoPrincipal findById(Long id) {
		
		AssuntoPrincipal assunto = findOrFail(id);
		return assunto;
	}
	
	public AssuntoPrincipal save(AssuntoPrincipal assunto) {
		
		Long classeId = assunto.getClasse().getId();
		ClasseProcesso classe = classeService.findById(classeId);
		assunto.setClasse(classe);
		
		assunto = assuntoPrincipalRepository.save(assunto);
		return assunto;
	}
	
	public void delete(Long id) {
		
		try {
			findOrFail(id);
			assuntoPrincipalRepository.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new AssuntoPrincipalEmUsoException(id);
		}
	}
	
	public List<AssuntoPrincipal> findByNome(String text) {
		
		List<AssuntoPrincipal> classes = assuntoPrincipalRepository.findByNomeContainingIgnoreCase(text);
		return classes;
	}
	
	private AssuntoPrincipal findOrFail(Long id) {
		
		AssuntoPrincipal classe = assuntoPrincipalRepository.findById(id)
				.orElseThrow(() -> new AssuntoPrincipalNaoEncontradoException(id));
		
		return classe;
	}
}
