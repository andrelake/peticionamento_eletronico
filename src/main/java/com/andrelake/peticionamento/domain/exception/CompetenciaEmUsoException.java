package com.andrelake.peticionamento.domain.exception;

public class CompetenciaEmUsoException extends EntidadeEmUsoException{

	private static final long serialVersionUID = 1L;

	public CompetenciaEmUsoException(String msg) {
		super(msg);
	}
	
	public CompetenciaEmUsoException(Long id) {
		this(String.format("Competência com id %d não pode ser removida, pois está em uso", id));
	}
}
