package org.serratec.grupo1.projetofinal.dto.tarefa;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

public class TarefaRequestDto {
	
	@Schema(description = "Identificação do usuario", required = true)
	private Long id;

	@Schema(description = "Nome do Projeto", required = true)
	private Long projeto;

	@Schema(description = "Nome do usuario", required = true)
	private Long usuario;
	
	@Schema(description = "Titulo do Projeto", required = true)
	private String titulo;

	@Schema(description = "Descrição do usuario", required = true)
	private String descricao;
	
	@Schema(description = "Data da criação do Projeto", required = true)
	private Date dataCriacao;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	public Long getIdUsuario() {
		return usuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.usuario = idUsuario;
	}

	public Long getIdProjeto() {
		return projeto;
	}

	public void setIdProjeto(Long idProjeto) {
		this.projeto = idProjeto;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

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
	
	
	

}
