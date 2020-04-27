package com.andrelake.peticionamento.domain.exception;

public class ClasseProcessoEmUsoException extends EntidadeEmUsoException{

	private static final long serialVersionUID = 1L;

	public ClasseProcessoEmUsoException(String msg) {
		super(msg);
	}
	
	public ClasseProcessoEmUsoException(Long id) {
		this(String.format("Classe de processo com id %d não pode ser removida, pois está em uso", id));
	}
}
