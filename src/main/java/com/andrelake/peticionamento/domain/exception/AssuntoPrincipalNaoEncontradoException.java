package com.andrelake.peticionamento.domain.exception;

public class AssuntoPrincipalNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public AssuntoPrincipalNaoEncontradoException(String msg) {
		super(msg);
	}
	
	public AssuntoPrincipalNaoEncontradoException(Long id) {
		this(String.format("Assunto principal com id %d não foi encontrado", id));
	}
}
