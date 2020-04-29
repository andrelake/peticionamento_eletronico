package com.andrelake.peticionamento.domain.repository.parte;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andrelake.peticionamento.domain.model.parte.Parte;

@Repository
public interface ParteRepository extends JpaRepository<Parte, Long>{

}
