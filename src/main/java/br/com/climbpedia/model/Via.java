package br.com.climbpedia.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vias")
public class Via {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private Long setor;
	
	private String graduacao;
	
	private Long tipo;
	
	@Column(name = "url_croqui")
	private String urlCroqui;
	
	@Column(name = "url_img")
	private String urlImg;
	
	private String tags;
	
	private Long conquistador;
	
	private String descricao;
	
	
	
	public Via() {}

//	public Via(String nome, String graduacao, String descricao) {
//		this.nome = nome;
//		this.graduacao = graduacao;
//		this.descricao = descricao;
//	}
	
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
		return setor;
	}

	public void setSetor(Long setor) {
		this.setor = setor;
	}

	public Long getTipo() {
		return tipo;
	}

	public void setTipo(Long tipo) {
		this.tipo = tipo;
	}

	public String getUrlCroqui() {
		return urlCroqui;
	}

	public void setUrlCroqui(String urlCroqui) {
		this.urlCroqui = urlCroqui;
	}

	public String getUrlImg() {
		return urlImg;
	}

	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Long getConquistador() {
		return conquistador;
	}

	public void setConquistador(Long conquistador) {
		this.conquistador = conquistador;
	}
	
	@Override
	public String toString() {
		return "Via -> nome: " + this.getNome();
	}
	
	public Map<String, String> validaCamposObrigatorios() {
		Map<String, String> erros = new HashMap<String, String>();
		
		if (this.nome.isBlank()) {
			erros.put("nome", "Nome deve ser preenchido");
		}
		if (this.descricao.isBlank()) {
			erros.put("descricao", "Descrição deve ser preenchida");
		}
		if (this.graduacao.isBlank()) {
			erros.put("graduacao", "Graduação deve ser preenchida");
		}
		
		return erros; 
	}

}
