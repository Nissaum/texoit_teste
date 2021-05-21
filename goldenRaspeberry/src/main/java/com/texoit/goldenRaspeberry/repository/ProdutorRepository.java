package com.texoit.goldenRaspeberry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.texoit.goldenRaspeberry.modelo.Produtores;

/**
 * ProdutorRepository
 * @author Vinicius Alvarenga
 *
 */
public interface ProdutorRepository extends JpaRepository<Produtores, Long> {
	
	Produtores findByNome(String nome);
	
	@Query("select p from Produtores p join p.filmes f where p.vezesPremiado > 1 order by p.nome, f.ano")
	List<Produtores> obterMaioresGanhadores();

}
