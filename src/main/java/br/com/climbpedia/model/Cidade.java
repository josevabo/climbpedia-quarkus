package br.com.climbpedia.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cidades")
public class Cidade {
	@Id @GeneratedValue
	private Long id;
	
	private String nome;
	
	private String uf; 
}
