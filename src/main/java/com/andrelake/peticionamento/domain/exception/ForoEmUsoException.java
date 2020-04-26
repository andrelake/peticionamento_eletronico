package com.andrelake.peticionamento.domain.exception;

public class ForoEmUsoException extends EntidadeEmUsoException{

	private static final long serialVersionUID = 1L;

	public ForoEmUsoException(String msg) {
		super(msg);
	}
	
	public ForoEmUsoException(Long id) {
		this(String.format("Foro com id %d não pode ser removido, pois está em uso", id));
	}
}
