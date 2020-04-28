package com.andrelake.peticionamento.domain.repository.parte;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andrelake.peticionamento.domain.model.parte.Nacionalidade;

@Repository
public interface NacionalidadeRepository extends JpaRepository<Nacionalidade, Long>{

	List<Nacionalidade> findByNomeContainingIgnoreCase(String text);
}
