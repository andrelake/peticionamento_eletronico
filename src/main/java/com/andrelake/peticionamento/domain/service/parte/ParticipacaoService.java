package com.andrelake.peticionamento.domain.service.parte;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.andrelake.peticionamento.domain.exception.ParticipacaoEmUsoException;
import com.andrelake.peticionamento.domain.exception.ParticipacaoNaoEncontradaException;
import com.andrelake.peticionamento.domain.model.parte.Participacao;
import com.andrelake.peticionamento.domain.repository.parte.ParticipacaoRepository;

@Service
public class ParticipacaoService {

	@Autowired
	private ParticipacaoRepository repo;
	
	public List<Participacao> findAll(){
		
		return repo.findAll();
	}
	
	public Participacao findById(Long id) {
		
		Participacao participacao = findOrFail(id);
		return participacao;
	}
	
	public Participacao save(Participacao participacao) {
		
		return repo.save(participacao);
	}
	
	public void deleteById(Long id) {
		
		findOrFail(id);
		try {
			repo.deleteById(id);
		}	
		catch(DataIntegrityViolationException e) {
			throw new ParticipacaoEmUsoException(id);
		}
	}
	
	public Participacao findOrFail(Long id) {
		
		Participacao participacao = repo.findById(id)
				.orElseThrow(() -> new ParticipacaoNaoEncontradaException(id));
		
		return participacao;
	}
}
