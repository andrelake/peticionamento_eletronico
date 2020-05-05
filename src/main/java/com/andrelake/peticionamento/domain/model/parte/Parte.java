package com.andrelake.peticionamento.domain.model.parte;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.andrelake.peticionamento.core.validation.Groups.ParticipacaoId;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@NotBlank
	private String cpf;
	@NotBlank
	private String rg;
	@NotBlank
	private String orgaoEmissor;
	@NotBlank
	private String nome;

	private char genero;
	@NotBlank
	private String cep;
	@NotBlank
	private String numeroLocal;
	private String complemento;
	
	@Valid
	@ConvertGroup(from = Default.class, to = ParticipacaoId.class)
	@NotNull
	@ManyToOne
	private Participacao participacao;
	
	@ManyToOne
	private EstadoCivil estadoCivil;
	@ManyToOne
	private Nacionalidade nacionalidade;
	@ManyToOne
	private Profissao profissao;
	
	@JsonIgnore
	@ManyToOne
	private Partes partesss;
}
