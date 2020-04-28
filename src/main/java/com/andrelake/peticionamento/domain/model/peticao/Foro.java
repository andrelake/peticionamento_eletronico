package com.andrelake.peticionamento.domain.model.peticao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Foro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	private String nome;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "foro_competencias",
			joinColumns = @JoinColumn(name = "foro_id"),
			inverseJoinColumns = @JoinColumn(name = "competencia_id"))
	private List<Competencia> competencias = new ArrayList<>();
}
