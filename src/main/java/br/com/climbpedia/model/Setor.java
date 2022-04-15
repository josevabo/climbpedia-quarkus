package br.com.climbpedia.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="setores")
public class Setor {
	@Id @GeneratedValue
	private Long id;
	
	@NotNull
	private String nome;
	
	private String descricao;
	
	@NotNull
	private Long cidade;
	
	private String geolocalizacao;
}
