package org.serratec.grupo1.projetofinal.model;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;


@Entity
@Table
public class Tarefa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id_tarefa")
	private Long id;
	
	@Column(name="titulo", length = 100)
	@NotBlank(message = "Digite um título válido")
	@Schema(description ="Titulo da Tarefa", required = true)
	private String titulo;

	@Column(name="descricao", length = 200)
	@NotBlank(message = "Digite uma descrição válida")
	@Schema(description ="Desrição da Tarefa", required = true)
	private String descricao;
	
	@Column(name="data_criacao")
	@PastOrPresent(message = "Digite uma data válida")
	@Schema(description ="Data de Criação da Tarefa", required = true)
	private Date dataCriacao;
	
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="id_usuario")
	private Usuario usuarioResp;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="id_projeto")
	private Projeto projeto;
	
	
	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}
	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	/**
	 * @return the dataCriacao
	 */
	public Date getDataCriacao() {
		return dataCriacao;
	}
	/**
	 * @param dataCriacao the dataCriacao to set
	 */
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarefa other = (Tarefa) obj;
		return Objects.equals(id, other.id);
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the usuarioResp
	 */
	public Usuario getUsuarioResp() {
		return usuarioResp;
	}
	/**
	 * @param usuarioResp the usuarioResp to set
	 */
	public void setUsuarioResp(Usuario usuarioResp) {
		this.usuarioResp = usuarioResp;
	}
	/**
	 * @return the projeto
	 */
	public Projeto getProjeto() {
		return projeto;
	}
	/**
	 * @param projeto the projeto to set
	 */
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
}