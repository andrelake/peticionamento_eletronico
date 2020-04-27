package com.andrelake.peticionamento.domain.exception;

public class AssuntoPrincipalEmUsoException extends EntidadeEmUsoException{

	private static final long serialVersionUID = 1L;

	public AssuntoPrincipalEmUsoException(String msg) {
		super(msg);
	}
	
	public AssuntoPrincipalEmUsoException(Long id) {
		this(String.format("Assunto principal com id %d não pode ser removido, pois está em uso", id));
	}
}
