package com.andrelake.peticionamento.domain.exception;

public class ForoNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public ForoNaoEncontradoException(String msg) {
		super(msg);
	}
	
	public ForoNaoEncontradoException(Long id) {
		this(String.format("Foro com id %d não foi encontrado", id));
	}
}
