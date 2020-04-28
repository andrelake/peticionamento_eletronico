package com.andrelake.peticionamento.domain.exception;

public class NacionalidadeNaoEncontradaException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public NacionalidadeNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public NacionalidadeNaoEncontradaException(Long id) {
		this(String.format("Participação com id %d não foi encontrada", id));
	}
}
