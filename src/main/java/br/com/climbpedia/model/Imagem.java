package br.com.climbpedia.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Imagem {
	@Id @GeneratedValue
	private Long id;
	
	private String legenda;
	
	private String url; 
}
