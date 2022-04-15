package br.com.climbpedia.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="usuarios")
public class Usuario {
	@Id @GeneratedValue
	private Long id;
	
	@NotNull
	private String nome;
	
	@Column(name="data_nasc")
	private Date dataNasc;
	
	@Column(name="cidade_id")
	private Long cidadeId;
}
