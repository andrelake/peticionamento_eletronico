package com.andrelake.peticionamento.domain.model.peticao;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.andrelake.peticionamento.core.validation.Groups.AssuntoPrincipalId;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class PeticaoInicialPrimeiroGrau {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@PositiveOrZero
	private BigDecimal valor;
	
	@Valid
	@ConvertGroup(from = Default.class, to = AssuntoPrincipalId.class)
	@NotNull
	@ManyToOne
	private AssuntoPrincipal assunto;
}
