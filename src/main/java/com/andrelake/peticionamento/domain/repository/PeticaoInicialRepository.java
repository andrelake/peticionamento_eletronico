package com.andrelake.peticionamento.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andrelake.peticionamento.domain.model.PeticaoInicialPrimeiroGrau;

@Repository
public interface PeticaoInicialRepository extends JpaRepository<PeticaoInicialPrimeiroGrau, Long>{

}
