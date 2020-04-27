package com.andrelake.peticionamento.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andrelake.peticionamento.domain.model.ClasseProcesso;

@Repository
public interface ClasseProcessoRepository extends JpaRepository<ClasseProcesso, Long>{

	List<ClasseProcesso> findByNomeContainingIgnoreCase(String text);
}
