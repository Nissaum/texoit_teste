package com.texoit.goldenRaspeberry.component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.texoit.goldenRaspeberry.dto.GanhadorDTO;
import com.texoit.goldenRaspeberry.dto.GanhadoresDTO;
import com.texoit.goldenRaspeberry.exception.ErroAplicacaoException;
import com.texoit.goldenRaspeberry.modelo.AnosPremiacao;
import com.texoit.goldenRaspeberry.modelo.Filme;
import com.texoit.goldenRaspeberry.modelo.Ganhador;
import com.texoit.goldenRaspeberry.modelo.Produtores;
import com.texoit.goldenRaspeberry.repository.ProdutorRepository;

/**
 * ProcessarResultadoComponent
 * @author Vinicius Alvarenga
 * 
 */
@Component
public class ProcessarResultadoComponent {
	
	@Autowired
	private ProdutorRepository produtorRepository;
	
	public GanhadoresDTO obterDTOComResultado() {
		GanhadoresDTO dto = new GanhadoresDTO();
		List<Produtores> produtores = produtorRepository.obterMaioresGanhadores();
		if (produtores != null) {
			preencherMAP(produtores.stream().distinct().collect(Collectors.toList()));
			
			dto.setMin(obterGanhadorMin(produtores));
			dto.setMax(obterGanhadorMax(produtores));
			return dto;
		} else {
			throw new ErroAplicacaoException("Não há dados disponíveis para obter o resultado!");
		}
	}
	
	public void preencherMAP(List<Produtores> produtores) {
		for (Produtores produtor : produtores) {
			for(Filme filme : produtor.getFilmes()) {
				if (Ganhador.GANHOU.equals(filme.getGanhador())) {
					
					if (produtor.getAnoUltimoPremio() == 0) {
						produtor.setAnoUltimoPremio(filme.getAno());
					} else {
						Integer intervalo = 0;
						Integer previousWin = 0;
						Integer followingWin = 0;
						
						if (produtor.getAnoUltimoPremio() > filme.getAno()) {
							intervalo = produtor.getAnoUltimoPremio() - filme.getAno();
							previousWin = filme.getAno();
							followingWin = produtor.getAnoUltimoPremio();
						} else {
							intervalo =  filme.getAno() - produtor.getAnoUltimoPremio();
							previousWin = produtor.getAnoUltimoPremio();
							followingWin = filme.getAno();
						}
						produtor.getAnosPremiacoesIntervalo().put(intervalo, new AnosPremiacao(previousWin, followingWin));
						produtor.getIntervalosPremios().add(intervalo);
						produtor.setAnoUltimoPremio(filme.getAno());
					}
				}
			}
		}
	}
	
	public List<GanhadorDTO> obterGanhadorMin(List<Produtores> produtores) {
		List<GanhadorDTO> ganhador = new ArrayList<GanhadorDTO>();
		Integer minimo = produtores.get(0).getIntervalosPremios().get(0);
		
		for (Produtores produtor : produtores) {
			for (Integer intervalo : produtor.getIntervalosPremios()) {
				if (intervalo < minimo) {
					ganhador.clear();
					minimo = intervalo;
					AnosPremiacao anosPremiacao = produtor.getAnosPremiacoesIntervalo().get(intervalo);
					ganhador.add(new GanhadorDTO(produtor.getNome(), intervalo, anosPremiacao.getPreviousWin(), anosPremiacao.getFollowingWin()));
				} else if(intervalo == minimo) {
					AnosPremiacao anosPremiacao = produtor.getAnosPremiacoesIntervalo().get(intervalo);
					GanhadorDTO ganhadorDTO = new GanhadorDTO(produtor.getNome(), intervalo, anosPremiacao.getPreviousWin(), anosPremiacao.getFollowingWin());
					
					if(!ganhador.contains(ganhadorDTO)) 
						ganhador.add(ganhadorDTO);
				}
			}
		}
		
		return ganhador;
	}
	
	public List<GanhadorDTO> obterGanhadorMax(List<Produtores> produtores) {
		List<GanhadorDTO> ganhador = new ArrayList<GanhadorDTO>();
		Integer max = produtores.get(0).getIntervalosPremios().get(0);
		
		for (Produtores produtor : produtores) {
			for (Integer intervalo : produtor.getIntervalosPremios()) {
				if (intervalo > max) {
					ganhador.clear();
					max = intervalo;
					AnosPremiacao anosPremiacao = produtor.getAnosPremiacoesIntervalo().get(intervalo);
					ganhador.add(new GanhadorDTO(produtor.getNome(), intervalo, anosPremiacao.getPreviousWin(), anosPremiacao.getFollowingWin()));
				} else if(intervalo == max) {
					AnosPremiacao anosPremiacao = produtor.getAnosPremiacoesIntervalo().get(intervalo);
					GanhadorDTO ganhadorDTO = new GanhadorDTO(produtor.getNome(), intervalo, anosPremiacao.getPreviousWin(), anosPremiacao.getFollowingWin());
					
					if(!ganhador.contains(ganhadorDTO)) 
						ganhador.add(ganhadorDTO);
				}
			}
		}
		
		return ganhador;
	}
}
