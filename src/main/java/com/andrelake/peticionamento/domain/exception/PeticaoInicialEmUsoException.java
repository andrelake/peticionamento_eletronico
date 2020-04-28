package com.andrelake.peticionamento.domain.exception;

public class PeticaoInicialEmUsoException extends EntidadeEmUsoException{

	private static final long serialVersionUID = 1L;

	public PeticaoInicialEmUsoException(String msg) {
		super(msg);
	}
	
	public PeticaoInicialEmUsoException(Long id) {
		this(String.format("Petição inicial de primeiro grau com id %d não pode ser removida, pois está em uso", id));
	}
}
