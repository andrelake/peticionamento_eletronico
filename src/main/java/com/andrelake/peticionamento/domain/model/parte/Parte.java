package com.andrelake.peticionamento.domain.model.parte;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Parte {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private char pessoa;
	private String cpf;
	private String rg;
	private String orgaoEmissor;
	private String nome;
	private char genero;
	private String cep;
	private String numeroLocal;
	private String complemento;
	
	@ManyToOne
	private Participacao participacao;
	@ManyToOne
	private EstadoCivil estadoCivil;
	@ManyToOne
	private Nacionalidade nacionalidade;
	@ManyToOne
	private Profissao profissao;
}
