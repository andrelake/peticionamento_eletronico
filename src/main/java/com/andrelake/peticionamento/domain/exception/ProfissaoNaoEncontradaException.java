package com.andrelake.peticionamento.domain.exception;

public class ProfissaoNaoEncontradaException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public ProfissaoNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public ProfissaoNaoEncontradaException(Long id) {
		this(String.format("Participação com id %d não foi encontrada", id));
	}
}
