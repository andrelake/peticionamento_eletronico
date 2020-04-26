package com.andrelake.peticionamento.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrelake.peticionamento.domain.exception.ForoNaoEncontradoException;
import com.andrelake.peticionamento.domain.model.Foro;
import com.andrelake.peticionamento.domain.repository.ForoRepository;

@Service
public class ForoService {

	@Autowired
	private ForoRepository repo;
	
	public List<Foro> findAll(){
		
		return repo.findAll();
	}
	
	public Foro findById(Long id) {
		
		Foro foro = findOrFail(id);
		return foro;
	}
	
	public Foro findOrFail(Long id) {
		
		Foro foro = repo.findById(id)
				.orElseThrow(() -> new ForoNaoEncontradoException(id));
		
		return foro;
	}
}
