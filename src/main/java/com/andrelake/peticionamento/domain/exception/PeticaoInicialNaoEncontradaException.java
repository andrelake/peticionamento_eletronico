package com.andrelake.peticionamento.domain.exception;

public class PeticaoInicialNaoEncontradaException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public PeticaoInicialNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public PeticaoInicialNaoEncontradaException(Long id) {
		this(String.format("Petição inicial de primeiro grau com id %d não foi encontrada", id));
	}
}
