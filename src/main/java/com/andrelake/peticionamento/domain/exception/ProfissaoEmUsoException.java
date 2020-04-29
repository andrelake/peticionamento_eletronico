package com.andrelake.peticionamento.domain.exception;

public class ProfissaoEmUsoException extends EntidadeEmUsoException{

	private static final long serialVersionUID = 1L;

	public ProfissaoEmUsoException(String msg) {
		super(msg);
	}
	
	public ProfissaoEmUsoException(Long id) {
		this(String.format("Profissão com id %d não pode ser removida, pois está em uso", id));
	}
}
