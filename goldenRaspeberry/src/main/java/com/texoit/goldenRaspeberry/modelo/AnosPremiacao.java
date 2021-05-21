package com.texoit.goldenRaspeberry.modelo;

public class AnosPremiacao {
	
	private Integer previousWin;
	private Integer followingWin;
	
	public AnosPremiacao() {
		
	}
	
	public AnosPremiacao(Integer previousWin, Integer followingWin) {
		super();
		this.previousWin = previousWin;
		this.followingWin = followingWin;
	}

	public Integer getPreviousWin() {
		return previousWin;
	}
	
	public void setPreviousWin(Integer previousWin) {
		this.previousWin = previousWin;
	}
	
	public Integer getFollowingWin() {
		return followingWin;
	}
	
	public void setFollowingWin(Integer followingWin) {
		this.followingWin = followingWin;
	}

}
