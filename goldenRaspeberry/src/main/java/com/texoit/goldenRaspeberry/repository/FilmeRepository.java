package com.texoit.goldenRaspeberry.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.texoit.goldenRaspeberry.modelo.Filme;

/**
 * FilmeRepository
 * @author Vinicius Alvarenga
 *
 */
public interface FilmeRepository extends JpaRepository<Filme, Long> {
	
	Filme findByTitulo(String titulo);

}
