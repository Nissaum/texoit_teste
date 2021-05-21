package com.texoit.goldenRaspeberry.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Filme
 * @author Vinicius Alvarenga
 *
 */
@Entity
public class Filme {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer ano;
	private String titulo;
	@JsonIgnore
	@ManyToMany
	@JoinTable(
	  name = "filme_estudio", 
	  joinColumns = @JoinColumn(name = "filme_id"), 
	  inverseJoinColumns = @JoinColumn(name = "estudio_id"))
	private List<Estudios> estudios;
	@JsonIgnore
	@ManyToMany
	@JoinTable(
	  name = "filme_produtor", 
	  joinColumns = @JoinColumn(name = "filme_id"), 
	  inverseJoinColumns = @JoinColumn(name = "produtor_id"))
	private List<Produtores> produtores;
	@Enumerated(EnumType.STRING)
	private Ganhador ganhador = Ganhador.PERDEU;
	
	public Filme() {
	}
	
	public Filme(Integer ano, String titulo, List<Estudios> estudios, List<Produtores> produtores, String ganhador) {
		this.ano = ano;
		this.titulo = titulo;
		this.estudios = estudios;
		this.produtores = produtores;
		this.ganhador = "yes".equals(ganhador) ? Ganhador.GANHOU : Ganhador.PERDEU;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Estudios> getEstudios() {
		return estudios;
	}

	public void setEstudios(List<Estudios> estudios) {
		this.estudios = estudios;
	}

	public List<Produtores> getProdutores() {
		return produtores;
	}

	public void setProdutores(List<Produtores> produtores) {
		this.produtores = produtores;
	}

	public Ganhador getGanhador() {
		return ganhador;
	}

	public void setGanhador(Ganhador ganhador) {
		this.ganhador = ganhador;
	}
	
	

}
