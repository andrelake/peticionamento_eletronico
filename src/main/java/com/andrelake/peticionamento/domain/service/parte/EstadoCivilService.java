package com.andrelake.peticionamento.domain.service.parte;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.andrelake.peticionamento.domain.exception.EstadoCivilEmUsoException;
import com.andrelake.peticionamento.domain.exception.EstadoCivilNaoEncontradoException;
import com.andrelake.peticionamento.domain.model.parte.EstadoCivil;
import com.andrelake.peticionamento.domain.repository.parte.EstadoCivilRepository;

@Service
public class EstadoCivilService {

	@Autowired
	private EstadoCivilRepository repo;
	
	public List<EstadoCivil> findAll(){
		
		return repo.findAll();
	}
	
	public EstadoCivil findById(Long id) {
		
		EstadoCivil estadoCivil = findOrFail(id);
		return estadoCivil;
	}
	
	public EstadoCivil save(EstadoCivil estadoCivil) {
		
		return repo.save(estadoCivil);
	}
	
	public void deleteById(Long id) {
		
		findOrFail(id);
		try {
			repo.deleteById(id);
		}	
		catch(DataIntegrityViolationException e) {
			throw new EstadoCivilEmUsoException(id);
		}
	}
	
	public EstadoCivil findOrFail(Long id) {
		
		EstadoCivil estadoCivil = repo.findById(id)
				.orElseThrow(() -> new EstadoCivilNaoEncontradoException(id));
		
		return estadoCivil;
	}
}
