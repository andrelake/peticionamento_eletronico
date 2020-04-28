package com.andrelake.peticionamento.domain.repository.peticao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andrelake.peticionamento.domain.model.peticao.Foro;

@Repository
public interface ForoRepository extends JpaRepository<Foro, Long>{

	List<Foro> findByNomeContainingIgnoreCase(String text);
}
