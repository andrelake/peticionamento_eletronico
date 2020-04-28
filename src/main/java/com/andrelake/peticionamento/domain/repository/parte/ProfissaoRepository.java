package com.andrelake.peticionamento.domain.repository.parte;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andrelake.peticionamento.domain.model.parte.Profissao;

@Repository
public interface ProfissaoRepository extends JpaRepository<Profissao, Long>{

	List<Profissao> findByNomeContainingIgnoreCase(String text);
}
