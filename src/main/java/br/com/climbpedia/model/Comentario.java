package br.com.climbpedia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name="comentarios")
public class Comentario {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@Column(name="datahora")
	private Timestamp dataHora;

	public Comentario() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getViaId() {
		return viaId;
	}

	public void setViaId(Long viaId) {
		this.viaId = viaId;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Timestamp getDataHora() {
		return dataHora;
	}

	public void setDataHora(Timestamp dataHora) {
		this.dataHora = dataHora;
	}

	@Override
	public String toString() {
		return "Comentario [dataHora=" + dataHora + ", id=" + id + ", texto=" + texto + ", usuarioId=" + usuarioId
				+ ", viaId=" + viaId + "]";
	}
}

