package com.texoit.goldenRaspeberry.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

/**
 * Produtores
 * @author Vinicius Alvarenga
 *
 */
@Entity
public class Produtores {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique=true)
	private String nome;
	@ManyToMany(mappedBy = "produtores")
	private List<Filme> filmes;
	private Integer vezesPremiado;
	@Transient
	private Integer anoUltimoPremio = 0;
	@Transient
	private List<Integer> intervalosPremios = new ArrayList<Integer>();
	@Transient
	private Map<Integer,AnosPremiacao> anosPremiacoesIntervalo = new HashMap<Integer,AnosPremiacao>();
	
	public Produtores() {
	}

	public Produtores(String nome, Integer vezesPremiado) {
		this.nome = nome;
		this.vezesPremiado = vezesPremiado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produtores other = (Produtores) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public Integer getVezesPremiado() {
		return vezesPremiado;
	}

	public void setVezesPremiado(Integer vezesPremiado) {
		this.vezesPremiado = vezesPremiado;
	}

	public List<Filme> getFilmes() {
		return filmes;
	}

	public void setFilmes(List<Filme> filmes) {
		this.filmes = filmes;
	}

	public List<Integer> getIntervalosPremios() {
		return intervalosPremios;
	}

	public void setIntervalosPremios(List<Integer> intervalosPremios) {
		this.intervalosPremios = intervalosPremios;
	}

	public Map<Integer, AnosPremiacao> getAnosPremiacoesIntervalo() {
		return anosPremiacoesIntervalo;
	}

	public void setAnosPremiacoesIntervalo(
			Map<Integer, AnosPremiacao> anosPremiacoesIntervalo) {
		this.anosPremiacoesIntervalo = anosPremiacoesIntervalo;
	}

	public Integer getAnoUltimoPremio() {
		return anoUltimoPremio;
	}

	public void setAnoUltimoPremio(Integer anoUltimoPremio) {
		this.anoUltimoPremio = anoUltimoPremio;
	}
	
	

}
