package com.andrelake.peticionamento.domain.service.parte;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.andrelake.peticionamento.domain.exception.NacionalidadeEmUsoException;
import com.andrelake.peticionamento.domain.exception.NacionalidadeNaoEncontradaException;
import com.andrelake.peticionamento.domain.model.parte.Nacionalidade;
import com.andrelake.peticionamento.domain.repository.parte.NacionalidadeRepository;

@Service
public class NacionalidadeService {

	@Autowired
	private NacionalidadeRepository repo;
	
	public List<Nacionalidade> findAll(){
		
		return repo.findAll();
	}
	
	public Nacionalidade findById(Long id) {
		
		Nacionalidade nacionalidade = findOrFail(id);
		return nacionalidade;
	}
	
	public Nacionalidade save(Nacionalidade nacionalidade) {
		
		return repo.save(nacionalidade);
	}
	
	public void deleteById(Long id) {
		
		findOrFail(id);
		try {
			repo.deleteById(id);
		}	
		catch(DataIntegrityViolationException e) {
			throw new NacionalidadeEmUsoException(id);
		}
	}
	
	public List<Nacionalidade> findByNome(String text) {
		
		List<Nacionalidade> nacionalidades = repo.findByNomeContainingIgnoreCase(text);
		
		return nacionalidades;
	}
	
	public Nacionalidade findOrFail(Long id) {
		
		Nacionalidade nacionalidade = repo.findById(id)
				.orElseThrow(() -> new NacionalidadeNaoEncontradaException(id));
		
		return nacionalidade;
	}
}
