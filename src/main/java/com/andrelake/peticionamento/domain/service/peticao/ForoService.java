package com.andrelake.peticionamento.domain.service.peticao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.andrelake.peticionamento.domain.exception.ForoEmUsoException;
import com.andrelake.peticionamento.domain.exception.ForoNaoEncontradoException;
import com.andrelake.peticionamento.domain.model.peticao.Foro;
import com.andrelake.peticionamento.domain.repository.peticao.ForoRepository;

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
	
	public Foro save(Foro foro) {
		
		return repo.save(foro);
	}
	
	public void deleteById(Long id) {
		
		findOrFail(id);
		try {
			repo.deleteById(id);
		}	
		catch(DataIntegrityViolationException e) {
			throw new ForoEmUsoException(id);
		}
	}
	
	public List<Foro> findByNome(String text) {
		
		List<Foro> foros = repo.findByNomeContainingIgnoreCase(text);
		
		return foros;
	}
	
	public Foro findOrFail(Long id) {
		
		Foro foro = repo.findById(id)
				.orElseThrow(() -> new ForoNaoEncontradoException(id));
		
		return foro;
	}
}
