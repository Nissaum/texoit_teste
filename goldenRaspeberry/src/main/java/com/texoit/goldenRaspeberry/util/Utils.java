package com.texoit.goldenRaspeberry.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Utils
 * @author Vinicius Alvarenga
 *
 */
public final class Utils {
	
	private static final String GANHADOR = "yes";
	
	public List<String> separarNomesVirgula(String campo) {
		String[] nomes = campo.split(",");
		
		return Arrays.asList(nomes);
	}
	
	public List<String> separarNomesVirgulasAnd(String campo) {
		List<String> listaRetorno = new ArrayList<String>();
		String[] nomes = campo.split(",");
		
		for(int i=0; i < nomes.length; i++) {
			List<String> lista = (Arrays.asList(nomes[i].split(" and ")));
			
			if(!lista.isEmpty())
				listaRetorno.addAll(lista);
		}
		
		return listaRetorno;
	}
	
	public Boolean verificaGanhouPremio(String premio) {
		if (GANHADOR.equals(premio))
			return Boolean.TRUE;
		return Boolean.FALSE;
	}

}
