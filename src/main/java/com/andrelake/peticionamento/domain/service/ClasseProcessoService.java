package com.andrelake.peticionamento.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.andrelake.peticionamento.domain.exception.ClasseProcessoEmUsoException;
import com.andrelake.peticionamento.domain.exception.ClasseProcessoNaoEncontradaException;
import com.andrelake.peticionamento.domain.model.ClasseProcesso;
import com.andrelake.peticionamento.domain.model.Competencia;
import com.andrelake.peticionamento.domain.repository.ClasseProcessoRepository;

@Service
public class ClasseProcessoService {

	@Autowired
	private CompetenciaService compService;
	
	@Autowired
	private ClasseProcessoRepository classeRepository;
	
	public List<ClasseProcesso> findAll(){
		
		return classeRepository.findAll();
	}
	
	public ClasseProcesso findById(Long id) {
		
		ClasseProcesso classe = findOrFail(id);
		return classe;
	}
	
	public ClasseProcesso save(ClasseProcesso classe) {
		
		Long compId = classe.getCompetencia().getId();
		Competencia comp = compService.findById(compId);
		classe.setCompetencia(comp);
		
		classe = classeRepository.save(classe);
		return classe;
	}
	
	public void delete(Long id) {
		
		try {
			findOrFail(id);
			classeRepository.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new ClasseProcessoEmUsoException(id);
		}
	}
	
	public List<ClasseProcesso> findByNome(String text) {
		
		List<ClasseProcesso> classes = classeRepository.findByNomeContainingIgnoreCase(text);
		return classes;
	}
	
	private ClasseProcesso findOrFail(Long id) {
		
		ClasseProcesso classe = classeRepository.findById(id)
				.orElseThrow(() -> new ClasseProcessoNaoEncontradaException(id));
		
		return classe;
	}
}
