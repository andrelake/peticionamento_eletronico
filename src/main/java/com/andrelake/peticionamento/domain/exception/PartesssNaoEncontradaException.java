package com.andrelake.peticionamento.domain.exception;

public class PartesssNaoEncontradaException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public PartesssNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public PartesssNaoEncontradaException(Long id) {
		this(String.format("Partes com id %d não foi encontrada", id));
	}
}
