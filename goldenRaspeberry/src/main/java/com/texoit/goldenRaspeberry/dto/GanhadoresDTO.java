package com.texoit.goldenRaspeberry.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * GanhadoresDTO
 * @author Vinicius Alvarenga
 *
 */
public class GanhadoresDTO {
	
	private List<GanhadorDTO> min = new ArrayList<GanhadorDTO>();
	private List<GanhadorDTO> max = new ArrayList<GanhadorDTO>();
	
	public List<GanhadorDTO> getMin() {
		return min;
	}

	public void setMin(List<GanhadorDTO> min) {
		this.min = min;
	}

	public List<GanhadorDTO> getMax() {
		return max;
	}

	public void setMax(List<GanhadorDTO> max) {
		this.max = max;
	}
	

}
