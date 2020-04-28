package com.andrelake.peticionamento.domain.service.parte;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.andrelake.peticionamento.domain.exception.ProfissaoEmUsoException;
import com.andrelake.peticionamento.domain.exception.ProfissaoNaoEncontradaException;
import com.andrelake.peticionamento.domain.model.parte.Profissao;
import com.andrelake.peticionamento.domain.repository.parte.ProfissaoRepository;

@Service
public class ProfissaoService {

	@Autowired
	private ProfissaoRepository repo;
	
	public List<Profissao> findAll(){
		
		return repo.findAll();
	}
	
	public Profissao findById(Long id) {
		
		Profissao profissao = findOrFail(id);
		return profissao;
	}
	
	public Profissao save(Profissao profissao) {
		
		return repo.save(profissao);
	}
	
	public void deleteById(Long id) {
		
		findOrFail(id);
		try {
			repo.deleteById(id);
		}	
		catch(DataIntegrityViolationException e) {
			throw new ProfissaoEmUsoException(id);
		}
	}
	
	public List<Profissao> findByNome(String text) {
		
		List<Profissao> profissaos = repo.findByNomeContainingIgnoreCase(text);
		
		return profissaos;
	}
	
	public Profissao findOrFail(Long id) {
		
		Profissao profissao = repo.findById(id)
				.orElseThrow(() -> new ProfissaoNaoEncontradaException(id));
		
		return profissao;
	}
}
