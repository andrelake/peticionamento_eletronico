package com.andrelake.peticionamento.domain.exception;

public class NacionalidadeEmUsoException extends EntidadeEmUsoException{

	private static final long serialVersionUID = 1L;

	public NacionalidadeEmUsoException(String msg) {
		super(msg);
	}
	
	public NacionalidadeEmUsoException(Long id) {
		this(String.format("Participação com id %d não pode ser removida, pois está em uso", id));
	}
}
