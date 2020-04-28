package com.andrelake.peticionamento.domain.exception;

public class EstadoCivilEmUsoException extends EntidadeEmUsoException{

	private static final long serialVersionUID = 1L;

	public EstadoCivilEmUsoException(String msg) {
		super(msg);
	}
	
	public EstadoCivilEmUsoException(Long id) {
		this(String.format("Participação com id %d não pode ser removida, pois está em uso", id));
	}
}
