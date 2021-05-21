package com.texoit.goldenRaspeberry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.texoit.goldenRaspeberry.component.ProcessarResultadoComponent;
import com.texoit.goldenRaspeberry.dto.GanhadoresDTO;

/**
 * GoldenRaspeberryController
 * @author Vinicius Alvarenga
 *
 */
@RestController
@RequestMapping("/golden_raspeberry")
public class GoldenRaspeberryController {
	
	@Autowired
	private ProcessarResultadoComponent processarResultadoComponent;
	
	@GetMapping
	public GanhadoresDTO obterFramboesas() {
		return processarResultadoComponent.obterDTOComResultado();
	}
	
	

}
