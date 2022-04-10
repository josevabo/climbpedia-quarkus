package br.com.climbpedia.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipos_via")
public class TipoVia {
	@Id @GeneratedValue
	private Long id;
	
	private String nome;
	
	private String descricao; 
}
