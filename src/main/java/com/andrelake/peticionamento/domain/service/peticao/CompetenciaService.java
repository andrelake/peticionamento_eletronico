package com.andrelake.peticionamento.domain.service.peticao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.andrelake.peticionamento.domain.exception.CompetenciaEmUsoException;
import com.andrelake.peticionamento.domain.exception.CompetenciaNaoEncontradaException;
import com.andrelake.peticionamento.domain.model.peticao.Competencia;
import com.andrelake.peticionamento.domain.repository.peticao.CompetenciaRepository;

@Service
public class CompetenciaService {
	
	@Autowired
	private CompetenciaRepository repo;
	
	public List<Competencia> findAll(){
		
		return repo.findAll();
	}
	
	public Competencia findById(Long id) {
		
		Competencia competencia = findOrFail(id);
		return competencia;
	}
	
	public Competencia save(Competencia competencia) {
		
		return repo.save(competencia);
	}
	
	public void deleteById(Long id) {
		
		findOrFail(id);
		try {
			repo.deleteById(id);
		}	
		catch(DataIntegrityViolationException e) {
			throw new CompetenciaEmUsoException(id);
		}
	}
	
	public Competencia findOrFail(Long id) {
		
		Competencia competencia = repo.findById(id)
				.orElseThrow(() -> new CompetenciaNaoEncontradaException(id));
		
		return competencia;
	}
}
