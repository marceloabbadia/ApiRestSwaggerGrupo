package org.serratec.grupo1.projetofinal.dto.projeto;

import io.swagger.v3.oas.annotations.media.Schema;

public class ProjetoRequestDto {
	
	@Schema(description = "Identificação do usuario", required = true)
	private Long id;
	
	@Schema(description = "Nome do Projeto", required = true)
	private String nomeProjeto;
	
	@Schema(description = "Descrição do Projeto", required = true)
	private String descProjeto;

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
