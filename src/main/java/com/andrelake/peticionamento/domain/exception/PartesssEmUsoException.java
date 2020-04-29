package com.andrelake.peticionamento.domain.exception;

public class PartesssEmUsoException extends EntidadeEmUsoException{

	private static final long serialVersionUID = 1L;

	public PartesssEmUsoException(String msg) {
		super(msg);
	}
	
	public PartesssEmUsoException(Long id) {
		this(String.format("Partes com id %d não pode ser removida, pois está em uso", id));
	}
}
