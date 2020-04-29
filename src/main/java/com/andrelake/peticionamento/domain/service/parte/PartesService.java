package com.andrelake.peticionamento.domain.service.parte;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.andrelake.peticionamento.domain.exception.PartesssEmUsoException;
import com.andrelake.peticionamento.domain.exception.PartesssNaoEncontradaException;
import com.andrelake.peticionamento.domain.model.parte.Parte;
import com.andrelake.peticionamento.domain.model.parte.Partes;
import com.andrelake.peticionamento.domain.model.peticao.PeticaoInicialPrimeiroGrau;
import com.andrelake.peticionamento.domain.repository.parte.PartesRepository;
import com.andrelake.peticionamento.domain.service.peticao.PeticaoInicialService;

@Service
public class PartesService {

	@Autowired
	private PeticaoInicialService peticaoService;
	@Autowired
	private PartesRepository partesRepository;
	
	public List<Partes> findAll() {
		
		List<Partes> list = partesRepository.findAll();
		return list;
	}
	
	public Partes findById(Long id) {
		
		Partes partes = findOrFail(id);
		return partes;
	}
	
	public Partes save(Partes partes) {
		
		Long peticaoId = partes.getPeticao().getId();
		PeticaoInicialPrimeiroGrau peticao = peticaoService.findById(peticaoId);
		partes.setPeticao(peticao);
		
		List<Parte> list = partes.getParteList();
		partes.setParteList(list);
		
		return partesRepository.save(partes);
	}
	
	public void delete(Long id) {
		
		findOrFail(id);
		try {
			partesRepository.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new PartesssEmUsoException(id);
		}
	}
	
	public Partes findOrFail(Long id) {
		
		Partes partes = partesRepository.findById(id)
				.orElseThrow(() -> new PartesssNaoEncontradaException(id));
		
		return partes;
	}
}
