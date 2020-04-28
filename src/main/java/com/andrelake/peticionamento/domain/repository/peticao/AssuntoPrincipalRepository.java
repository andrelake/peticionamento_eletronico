package com.andrelake.peticionamento.domain.repository.peticao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andrelake.peticionamento.domain.model.peticao.AssuntoPrincipal;

@Repository
public interface AssuntoPrincipalRepository extends JpaRepository<AssuntoPrincipal, Long>{

	List<AssuntoPrincipal> findByNomeContainingIgnoreCase(String text);
}
