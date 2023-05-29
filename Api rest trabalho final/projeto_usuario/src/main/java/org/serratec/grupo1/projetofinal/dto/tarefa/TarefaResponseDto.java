package org.serratec.grupo1.projetofinal.dto.tarefa;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

public class TarefaResponseDto {
	
	@Schema(description = "Titulo da Tarefa", required = true)
	private String titulo;

	@Schema(description = "Descrição da Tarefa", required = true)
	private String descricao;
	
	@Schema(description = "Data da Criação da Tarefa", required = true)
	private Date dataCriacao;

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
