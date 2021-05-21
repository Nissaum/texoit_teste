package com.texoit.goldenRaspeberry.modelo;

/**
 * MassaBruta
 * @author Vinicius Alvarenga
 *
 */
public class MassaBruta {
	
	private Integer year;
	private String title;
	private String studios;
	private String producers;
	private String winner;
	
	public MassaBruta(Integer year, String title, String studios,
			String producers, String winner) {
		this.year = year;
		this.title = title;
		this.studios = studios;
		this.producers = producers;
		this.winner = winner;
	}
	
	public MassaBruta(Integer year, String title, String studios,
			String producers) {
		this.year = year;
		this.title = title;
		this.studios = studios;
		this.producers = producers;
	}
	
	public Integer getYear() {
		return year;
	}
	
	public void setYear(Integer year) {
		this.year = year;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getStudios() {
		return studios;
	}
	
	public void setStudios(String studios) {
		this.studios = studios;
	}
	
	public String getProducers() {
		return producers;
	}
	
	public void setProducers(String producers) {
		this.producers = producers;
	}
	
	public String getWinner() {
		return winner;
	}
	
	public void setWinner(String winner) {
		this.winner = winner;
	}
	
	

}
