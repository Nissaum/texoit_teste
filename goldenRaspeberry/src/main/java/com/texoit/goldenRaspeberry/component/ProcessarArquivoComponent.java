package com.texoit.goldenRaspeberry.component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.texoit.goldenRaspeberry.exception.ErroAplicacaoException;
import com.texoit.goldenRaspeberry.modelo.Estudios;
import com.texoit.goldenRaspeberry.modelo.Filme;
import com.texoit.goldenRaspeberry.modelo.MassaBruta;
import com.texoit.goldenRaspeberry.modelo.Produtores;
import com.texoit.goldenRaspeberry.repository.EstudioRepository;
import com.texoit.goldenRaspeberry.repository.FilmeRepository;
import com.texoit.goldenRaspeberry.repository.ProdutorRepository;
import com.texoit.goldenRaspeberry.util.Utils;

/**
 * ProcessarArquivoComponent
 * @author Vinicius Alvarenga
 *
 */
@Component
public class ProcessarArquivoComponent {
	
	@Autowired
	private FilmeRepository filmeRepository;
	
	@Autowired
	private EstudioRepository estudioRepository;
	
	@Autowired
	private ProdutorRepository produtorRepository;

	@EventListener(ApplicationReadyEvent.class)
	public void lerArquivoCSV() throws IOException {

		try{
			BufferedReader reader = Files.newBufferedReader(Paths.get("C:\\movielist.csv"));

			String linha;
			List<MassaBruta> massaLista = new ArrayList<MassaBruta>();
			
			reader.readLine();
			while ((linha = reader.readLine()) != null) {
				System.out.println(linha);
				
				String[] registroLinha = linha.split(";");
				if (registroLinha.length > 4) {
					massaLista.add(new MassaBruta(Integer.parseInt(registroLinha[0]), registroLinha[1], registroLinha[2], registroLinha[3], registroLinha[4]));
				} else {
					massaLista.add(new MassaBruta(Integer.parseInt(registroLinha[0]), registroLinha[1], registroLinha[2], registroLinha[3]));
				}
			}
			
			popularEntidadesBanco(massaLista);
		} catch (NoSuchFileException e) {
			throw new ErroAplicacaoException("Não foi encontrado o arquivo para a leitura dos dados!!");
		}
		
	}
	
	@Transactional
	public void popularEntidadesBanco(List<MassaBruta> lista) {
		for (MassaBruta massa : lista) {
			List<Estudios> estudios = obterEstudios(massa.getStudios());
			List<Produtores> produtores = obterProdutores(massa.getProducers(), massa.getWinner());
			filmeRepository.saveAndFlush(new Filme(massa.getYear(), massa.getTitle(), estudios, produtores, massa.getWinner()));
		}
	}
	
	@Transactional
	private List<Estudios> obterEstudios(String nomes) {
		List<Estudios> listaEstudios = new ArrayList<Estudios>();
		List<String> estudios = new Utils().separarNomesVirgula(nomes);
		
		if (estudios != null && !estudios.isEmpty()) 
			for (String estudio : estudios) {
				String nomeEstudio = estudio.replaceAll("\\s+$", "");
				nomeEstudio = nomeEstudio.replaceAll("^\\s+", "");
				Estudios estudioDB = estudioRepository.findByNome(nomeEstudio);
				if (estudioDB == null) {
					listaEstudios.add(estudioRepository.saveAndFlush(new Estudios(nomeEstudio)));
				} else {
					listaEstudios.add(estudioDB);
				}
				
			}
		
		return listaEstudios;
	}
	
	@Transactional
	private List<Produtores> obterProdutores(String nomes, String ganhou) {
		List<Produtores> listaProdutores = new ArrayList<Produtores>();
		List<String> produtores = new Utils().separarNomesVirgulasAnd(nomes);
		
		if (produtores != null && !produtores.isEmpty()) {
			for (String produtor : produtores) {
				if(!produtor.trim().isEmpty()) {
					String nomeProdutor = produtor.replaceAll("\\s+$", "");
					nomeProdutor = nomeProdutor.replaceAll("^\\s+", "");
					Produtores produtorDB = produtorRepository.findByNome(nomeProdutor);
					if (produtorDB == null) {
						listaProdutores.add(produtorRepository.saveAndFlush(new Produtores(nomeProdutor, new Utils().verificaGanhouPremio(ganhou) ? 1 : 0)));
					} else {
						if (new Utils().verificaGanhouPremio(ganhou))
							produtorRepository.saveAndFlush(atualizarProdutor(produtorDB));
						listaProdutores.add(produtorDB);
					}
				}
			}
		}

		return listaProdutores;
	}
	
	@Transactional
	private Produtores atualizarProdutor(Produtores produtor) {
		produtor.setVezesPremiado(produtor.getVezesPremiado()+1);
		return produtor;
	}

}
