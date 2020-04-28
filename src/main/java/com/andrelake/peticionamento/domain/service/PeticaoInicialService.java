package com.andrelake.peticionamento.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.andrelake.peticionamento.domain.exception.PeticaoInicialEmUsoException;
import com.andrelake.peticionamento.domain.exception.PeticaoInicialNaoEncontradaException;
import com.andrelake.peticionamento.domain.model.AssuntoPrincipal;
import com.andrelake.peticionamento.domain.model.PeticaoInicialPrimeiroGrau;
import com.andrelake.peticionamento.domain.repository.PeticaoInicialRepository;

@Service
public class PeticaoInicialService {
	
	@Autowired
	private AssuntoPrincipalService assuntoService;
	
	@Autowired
	private PeticaoInicialRepository peticaoRepo;
	
	public List<PeticaoInicialPrimeiroGrau> findAll(){
		
		return peticaoRepo.findAll();
	}
	
	public PeticaoInicialPrimeiroGrau findById(Long id) {
		
		PeticaoInicialPrimeiroGrau peticao = findOrFail(id);
		return peticao;
	}
	
	public PeticaoInicialPrimeiroGrau save(PeticaoInicialPrimeiroGrau peticao) {
		
		Long assuntoId = peticao.getAssunto().getId();
		AssuntoPrincipal assunto = assuntoService.findById(assuntoId);
		
		peticao.setAssunto(assunto);
		return peticaoRepo.save(peticao);
	}
	
	public void deleteById(Long id) {
		
		findOrFail(id);
		try {
			peticaoRepo.deleteById(id);
		}	
		catch(DataIntegrityViolationException e) {
			throw new PeticaoInicialEmUsoException(id);
		}
	}
	
	public PeticaoInicialPrimeiroGrau findOrFail(Long id) {
		
		PeticaoInicialPrimeiroGrau peticao = peticaoRepo.findById(id)
				.orElseThrow(() -> new PeticaoInicialNaoEncontradaException(id));
		
		return peticao;
	}
}
