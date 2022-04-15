package br.com.climbpedia.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="vias")
public class Via {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String nome;
	
	@NotNull
	@Column(name="setor_id")
	private Long setorId;
	
	@NotNull
	private String graduacao;
	
	@NotNull
	@Column(name="tipo_id")
	private Long tipoId;
	
	@Column(name = "url_croqui")
	private String urlCroqui;
	
	@Column(name = "imagem_id")
	private String imagemId;
	
	private String tags;
	
	@Column(name="conquistador_id")
	private Long conquistadorId;
	
	private String descricao;
	
	@Column(name = "dt_conquista")
	private LocalDate dtConquista;
	
	private Short extensao;
	
	public Via() {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getGraduacao() {
		return graduacao;
	}

	public void setGraduacao(String graduacao) {
		this.graduacao = graduacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Long getSetor() {
		return setorId;
	}

	public void setSetor(Long setorId) {
		this.setorId = setorId;
	}

	public Long getTipo() {
		return tipoId;
	}

	public void setTipo(Long tipoId) {
		this.tipoId = tipoId;
	}

	public String getUrlCroqui() {
		return urlCroqui;
	}

	public void setUrlCroqui(String urlCroqui) {
		this.urlCroqui = urlCroqui;
	}

	public String getUrlImg() {
		return imagemId;
	}

	public void setUrlImg(String imagemId) {
		this.imagemId = imagemId;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Long getConquistador() {
		return conquistadorId;
	}

	public void setConquistador(Long conquistadorId) {
		this.conquistadorId = conquistadorId;
	}
	
	@Override
	public String toString() {
		return "Via -> nome: " + this.getNome();
	}
	
	public LocalDate getDtConquista() {
		return dtConquista;
	}

	public void setDtConquista(LocalDate dtConquista) {
		this.dtConquista = dtConquista;
	}

	public Short getExtensao() {
		return extensao;
	}

	public void setExtensao(Short extensao) {
		this.extensao = extensao;
	}
	

}
