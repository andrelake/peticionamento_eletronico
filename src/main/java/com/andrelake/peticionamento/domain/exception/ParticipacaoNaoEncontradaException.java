package com.andrelake.peticionamento.domain.exception;

public class ParticipacaoNaoEncontradaException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public ParticipacaoNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public ParticipacaoNaoEncontradaException(Long id) {
		this(String.format("Participação com id %d não foi encontrada", id));
	}
}
