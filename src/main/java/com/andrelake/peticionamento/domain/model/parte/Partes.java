package com.andrelake.peticionamento.domain.model.parte;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.andrelake.peticionamento.domain.model.peticao.PeticaoInicialPrimeiroGrau;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Partes {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private PeticaoInicialPrimeiroGrau peticao;
	
	@ManyToMany
	@JoinTable(name = "partes_parteid",
			joinColumns = @JoinColumn(name = "partes_id"),
			inverseJoinColumns = @JoinColumn(name = "parte_id"))
	private List<Parte> parteList = new ArrayList<>();
}
