package com.andrelake.peticionamento.domain.exception;

public class ParteEmUsoException extends EntidadeEmUsoException{

	private static final long serialVersionUID = 1L;

	public ParteEmUsoException(String msg) {
		super(msg);
	}
	
	public ParteEmUsoException(Long id) {
		this(String.format("Parte com id %d não pode ser removida, pois está em uso", id));
	}
}
