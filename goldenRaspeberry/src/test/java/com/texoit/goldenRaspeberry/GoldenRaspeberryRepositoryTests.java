package com.texoit.goldenRaspeberry;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.texoit.goldenRaspeberry.modelo.Estudios;
import com.texoit.goldenRaspeberry.modelo.Filme;
import com.texoit.goldenRaspeberry.modelo.Produtores;
import com.texoit.goldenRaspeberry.repository.EstudioRepository;
import com.texoit.goldenRaspeberry.repository.FilmeRepository;
import com.texoit.goldenRaspeberry.repository.ProdutorRepository;

/**
 * GoldenRaspeberryRepositoryTests
 * @author GoldenRaspeberryRepositoryTests
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class GoldenRaspeberryRepositoryTests {
	
	@Autowired
	private FilmeRepository filmeRepository;
	
	@Autowired
	private EstudioRepository estudioRepository;
	
	@Autowired
	private ProdutorRepository produtorRepository;
	
	@Autowired
	private TestEntityManager em;

	@Test
	public void deveriaCarregarUmFilmeAoBuscarPeloSeuTitulo() {
		String nomeFilme = "O Senhor dos anéis";
		
		Filme filme = new Filme();
		filme.setTitulo(nomeFilme);
		filme.setAno(2002);
		em.persist(filme);
		
		Filme filmeDB = filmeRepository.findByTitulo(nomeFilme);
		Assert.assertNotNull(filmeDB);
		Assert.assertEquals(nomeFilme, filmeDB.getTitulo());
	}
	
	@Test
	public void deveriaCarregarUmEstudioAoBuscarPeloSeuNome() {
		String nomeEstudio = "Disney";
		
		Estudios estudio = new Estudios(nomeEstudio);
		em.persist(estudio);
		
		Estudios estudioDB = estudioRepository.findByNome(nomeEstudio);
		Assert.assertNotNull(estudioDB);
		Assert.assertEquals(nomeEstudio, estudioDB.getNome());
	}
	
	@Test
	public void deveriaCarregarUmProdutorAoBuscarPeloSeuNome() {
		String nomeProdutor = "Steven Spielberg";
		
		Produtores produtor = new Produtores(nomeProdutor, 5);
		em.persist(produtor);
		
		Produtores produtorDB = produtorRepository.findByNome(nomeProdutor);
		Assert.assertNotNull(produtorDB);
		Assert.assertEquals(nomeProdutor, produtorDB.getNome());
	}

}
