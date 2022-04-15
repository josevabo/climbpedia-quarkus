package br.com.climbpedia.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="cidades")
public class Cidade {
	@Id @GeneratedValue
	private Long id;
	
	@NotNull
	private String nome;
	
	@NotNull
	private String uf; 
}
