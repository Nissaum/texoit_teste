package com.texoit.goldenRaspeberry.component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.texoit.goldenRaspeberry.dto.GanhadorDTO;
import com.texoit.goldenRaspeberry.dto.GanhadoresDTO;
import com.texoit.goldenRaspeberry.exception.ErroAplicacaoException;
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
			preencherDTO(produtores);
			
			dto.setMin(obterGanhadorMin(produtores));
			dto.setMax(obterGanhadorMax(produtores));
			return dto;
		} else {
			throw new ErroAplicacaoException("Não há dados disponíveis para obter o resultado!");
		}
	}
	
	public void preencherDTO(List<Produtores> produtores) {
		for (Produtores produtor : produtores) {
			for(Filme filme : produtor.getFilmes()) {
				if (Ganhador.GANHOU.equals(filme.getGanhador())) {
					produtor.getDto().setProducer(produtor.getNome());
					if (produtor.getDto().getFollowingWin() == 0
							&& produtor.getDto().getPreviousWin() == 0) {
						produtor.getDto().setFollowingWin(filme.getAno());
						produtor.getDto().setPreviousWin(filme.getAno());
						produtor.setIntervaloPremio(Integer.MAX_VALUE);
					} else {
						if (produtor.getDto().getFollowingWin() < filme.getAno()) {
							produtor.getDto().setFollowingWin(filme.getAno());
						} else if (produtor.getDto().getPreviousWin() > filme.getAno()) {
							produtor.getDto().setPreviousWin(filme.getAno());
						}
					}
					
					if((produtor.getDto().getFollowingWin() - produtor.getDto().getPreviousWin()) > 0)
						if (produtor.getDto().getInterval() == 0 
							|| produtor.getDto().getInterval() > (produtor.getDto().getFollowingWin() - produtor.getDto().getPreviousWin())){
							produtor.getDto().setInterval(produtor.getDto().getFollowingWin() - produtor.getDto().getPreviousWin());
							produtor.setIntervaloPremio(produtor.getDto().getInterval());
						}
				}
			}
		}
	}
	
	public List<GanhadorDTO> obterGanhadorMin(List<Produtores> produtores) {
		List<GanhadorDTO> ganhador = new ArrayList<GanhadorDTO>();
		Integer minimo = produtores.get(0).getIntervaloPremio();
		
		for (Produtores produtor : produtores) {
			if (produtor.getIntervaloPremio() < minimo) {
				ganhador.clear();
				ganhador.add(produtor.getDto());
			} else if(produtor.getIntervaloPremio() == minimo) {
				if(!ganhador.contains(produtor.getDto()))
					ganhador.add(produtor.getDto());
			}
		}
		
		return ganhador;
	}
	
	public List<GanhadorDTO> obterGanhadorMax(List<Produtores> produtores) {
		List<GanhadorDTO> ganhador = new ArrayList<GanhadorDTO>();
		Integer maximo = produtores.get(0).getIntervaloPremio();
		
		for (Produtores produtor : produtores) {
			if (produtor.getIntervaloPremio() > maximo) {
				ganhador.clear();
				ganhador.add(produtor.getDto());
			} else if(produtor.getIntervaloPremio() == maximo) {
				if(!ganhador.contains(produtor.getDto()))
					ganhador.add(produtor.getDto());
			}
		}
		
		return ganhador;
	}
}
