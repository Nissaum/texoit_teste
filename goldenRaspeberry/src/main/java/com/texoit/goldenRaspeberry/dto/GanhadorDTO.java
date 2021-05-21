package com.texoit.goldenRaspeberry.dto;

/**
 * GanhadorDTO
 * @author Vinicius Alvarenga
 *
 */
public class GanhadorDTO {
	
	private String producer;
	private int interval;
	private int previousWin;
	private int followingWin;
	
	public GanhadorDTO() {
	}
	
	public GanhadorDTO(String producer, int interval, int previousWin, int followingWin) {
		super();
		this.producer = producer;
		this.interval = interval;
		this.previousWin = previousWin;
		this.followingWin = followingWin;
	}
	
	public String getProducer() {
		return producer;
	}
	
	public void setProducer(String producer) {
		this.producer = producer;
	}
	
	public int getInterval() {
		return interval;
	}
	
	public void setInterval(int interval) {
		this.interval = interval;
	}
	
	public int getPreviousWin() {
		return previousWin;
	}
	
	public void setPreviousWin(int previousWin) {
		this.previousWin = previousWin;
	}
	
	public int getFollowingWin() {
		return followingWin;
	}
	
	public void setFollowingWin(int followingWin) {
		this.followingWin = followingWin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + followingWin;
		result = prime * result + interval;
		result = prime * result + previousWin;
		result = prime * result
				+ ((producer == null) ? 0 : producer.hashCode());
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
		GanhadorDTO other = (GanhadorDTO) obj;
		if (followingWin != other.followingWin)
			return false;
		if (interval != other.interval)
			return false;
		if (previousWin != other.previousWin)
			return false;
		if (producer == null) {
			if (other.producer != null)
				return false;
		} else if (!producer.equals(other.producer))
			return false;
		return true;
	}

}
