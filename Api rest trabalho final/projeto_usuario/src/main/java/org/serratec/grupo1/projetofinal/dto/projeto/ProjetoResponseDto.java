package org.serratec.grupo1.projetofinal.dto.projeto;

import io.swagger.v3.oas.annotations.media.Schema;

public class ProjetoResponseDto {
	
	@Schema(description = "Nome do Projeto", required = true)
	private String nomeProjeto;
	
	@Schema(description = "Descrição do Projeto", required = true)
	private String descProjeto;

	/**
	 * @return the nomeProjeto
	 */
	public String getNomeProjeto() {
		return nomeProjeto;
	}

	/**
	 * @param nomeProjeto the nomeProjeto to set
	 */
	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}

	/**
	 * @return the descProjeto
	 */
	public String getDescProjeto() {
		return descProjeto;
	}

	/**
	 * @param descProjeto the descProjeto to set
	 */
	public void setDescProjeto(String descProjeto) {
		this.descProjeto = descProjeto;
	}
	
	

}
