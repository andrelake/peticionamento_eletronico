package com.andrelake.peticionamento.domain.model.peticao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.andrelake.peticionamento.core.validation.Groups.ClasseProcessoId;
import com.andrelake.peticionamento.core.validation.Groups.CompetenciaId;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class ClasseProcesso {

	@NotNull(groups = ClasseProcessoId.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotBlank
	private String nome;
	
	@Valid
	@ConvertGroup(from = Default.class, to = CompetenciaId.class)
	@NotNull
	@ManyToOne
	private Competencia competencia;
	
	@JsonIgnore
	@OneToMany(mappedBy = "classe")
	private List<AssuntoPrincipal> assuntos = new ArrayList<>();
}
