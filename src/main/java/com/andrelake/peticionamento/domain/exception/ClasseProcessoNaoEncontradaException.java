package com.andrelake.peticionamento.domain.exception;

public class ClasseProcessoNaoEncontradaException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public ClasseProcessoNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public ClasseProcessoNaoEncontradaException(Long id) {
		this(String.format("Classe de processo com id %d não foi encontrada", id));
	}
}
