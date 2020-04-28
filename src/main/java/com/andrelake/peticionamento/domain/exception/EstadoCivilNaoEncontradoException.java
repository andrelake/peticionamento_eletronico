package com.andrelake.peticionamento.domain.exception;

public class EstadoCivilNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public EstadoCivilNaoEncontradoException(String msg) {
		super(msg);
	}
	
	public EstadoCivilNaoEncontradoException(Long id) {
		this(String.format("Participação com id %d não foi encontrada", id));
	}
}
