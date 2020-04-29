package com.andrelake.peticionamento.domain.service.parte;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.andrelake.peticionamento.domain.exception.ParteEmUsoException;
import com.andrelake.peticionamento.domain.exception.ParteNaoEncontradaException;
import com.andrelake.peticionamento.domain.model.parte.EstadoCivil;
import com.andrelake.peticionamento.domain.model.parte.Nacionalidade;
import com.andrelake.peticionamento.domain.model.parte.Parte;
import com.andrelake.peticionamento.domain.model.parte.Participacao;
import com.andrelake.peticionamento.domain.model.parte.Profissao;
import com.andrelake.peticionamento.domain.repository.parte.ParteRepository;

@Service
public class ParteService {

	@Autowired
	private EstadoCivilService estadoService;
	@Autowired
	private NacionalidadeService nacService;
	@Autowired
	private ParticipacaoService partService;
	@Autowired
	private ProfissaoService profService;
	
	@Autowired
	private ParteRepository parteRepository;
	
	public List<Parte> findAll() {
		
		List<Parte> list = parteRepository.findAll();
		return list;
	}
	
	public Parte findById(Long id) {
		
		Parte parte = findOrFail(id);
		return parte;
	}
	
	public Parte save(Parte parte) {
		
		Long estadoId = parte.getEstadoCivil().getId();
		EstadoCivil est = estadoService.findById(estadoId);
		parte.setEstadoCivil(est);

		Long nacId = parte.getNacionalidade().getId();
		Nacionalidade nac = nacService.findById(nacId);
		parte.setNacionalidade(nac);
		
		Long partId = parte.getParticipacao().getId();
		Participacao part = partService.findById(partId);
		parte.setParticipacao(part);
		
		Long profId = parte.getProfissao().getId();
		Profissao prof = profService.findById(profId);
		parte.setProfissao(prof);
		
		parte = parteRepository.save(parte);
		return parte;
	}
	
	public void delete(Long id) {
		
		findOrFail(id);
		try {
			parteRepository.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new ParteEmUsoException(id);
		}
	}
	public Parte findOrFail(Long id) {
		
		Parte parte = parteRepository.findById(id)
				.orElseThrow(() -> new ParteNaoEncontradaException(id));
		
		return parte;
	}
}
