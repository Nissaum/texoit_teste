package com.texoit.goldenRaspeberry.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.texoit.goldenRaspeberry.modelo.Estudios;

/**
 * EstudioRepository
 * @author Vinicius Alvarenga
 *
 */
public interface EstudioRepository extends JpaRepository<Estudios, Long> {
	
	Estudios findByNome(String nome);

}
