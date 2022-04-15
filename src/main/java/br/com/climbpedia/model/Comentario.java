package br.com.climbpedia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.Calendar;

@Entity
@Table(name="comentarios")
public class Comentario {
	@Id @GeneratedValue
	private Long id;
	
	@NotNull
	@Column(name="via_id")
	private Long viaId;
	
	@NotNull
	private String texto;
	
	@NotNull
	@Column(name="usuario_id")
	private Long usuarioId;
	
	@NotNull
	private Calendar dataHora;
}

