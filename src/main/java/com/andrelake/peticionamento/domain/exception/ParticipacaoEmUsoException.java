package com.andrelake.peticionamento.domain.exception;

public class ParticipacaoEmUsoException extends EntidadeEmUsoException{

	private static final long serialVersionUID = 1L;

	public ParticipacaoEmUsoException(String msg) {
		super(msg);
	}
	
	public ParticipacaoEmUsoException(Long id) {
		this(String.format("Participação com id %d não pode ser removida, pois está em uso", id));
	}
}
