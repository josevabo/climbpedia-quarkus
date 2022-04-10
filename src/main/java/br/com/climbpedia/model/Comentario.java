package br.com.climbpedia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Calendar;

@Entity
@Table(name="comentarios")
public class Comentario {
	@Id @GeneratedValue
	private Long id;
	
	@Column(name="via_id")
	private Long viaId;
	
	private String texto;
	
	@Column(name="usuario_id")
	private Long usuarioId;
	
	private Calendar dataHora;
}

