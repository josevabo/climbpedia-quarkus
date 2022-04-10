package br.com.climbpedia.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Usuario {
	@Id @GeneratedValue
	private Long id;
	
	private String nome;
	
	private Date dataNasc;
	
	private String cidade;
}
