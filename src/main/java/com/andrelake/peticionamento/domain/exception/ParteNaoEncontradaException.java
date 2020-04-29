package com.andrelake.peticionamento.domain.exception;

public class ParteNaoEncontradaException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public ParteNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public ParteNaoEncontradaException(Long id) {
		this(String.format("Parte com id %d não foi encontrada", id));
	}
}
