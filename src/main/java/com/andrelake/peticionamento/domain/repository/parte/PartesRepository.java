package com.andrelake.peticionamento.domain.repository.parte;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andrelake.peticionamento.domain.model.parte.Partes;

@Repository
public interface PartesRepository extends JpaRepository<Partes, Long>{

}
