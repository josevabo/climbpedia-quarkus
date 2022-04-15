package br.com.climbpedia.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="tipos_via")
public class TipoVia {
	@Id @GeneratedValue
	private Long id;
	
	@NotNull
	private String nome;
	
	@NotNull
	private String descricao; 
}
