package br.com.climbpedia.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Setor {
	@Id @GeneratedValue
	private Long id;
	
	private String nome;
	
	private String descricao;
	
	private String cidade;
	
	private String geolocalizacao;
}
