package com.andrelake.peticionamento.domain.exception;

public class CompetenciaNaoEncontradaException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public CompetenciaNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public CompetenciaNaoEncontradaException(Long id) {
		this(String.format("Competência com id %d não foi encontrada", id));
	}
}
