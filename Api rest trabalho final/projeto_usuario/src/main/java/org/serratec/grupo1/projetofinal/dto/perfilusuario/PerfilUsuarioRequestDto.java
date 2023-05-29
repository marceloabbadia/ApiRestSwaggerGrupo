package org.serratec.grupo1.projetofinal.dto.perfilusuario;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

public class PerfilUsuarioRequestDto {
	
	@Schema(description ="Identificação do Usurio", required = true)
	private Long id;

 	@Schema(description ="Nome do usuario", required = true)
	private Long usuario;
	
	@Schema(description ="Nome completo do usuario", required = true)
	private String nomeCompleto;
	
	@Schema(description = "data de usuario", required = true)
	private Date dataNasc;
	
	@Schema(description = "Nome do usuario", required = true)
	private String genero;

	public Long getIdUsuario() {
		return usuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.usuario = idUsuario;
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
	 * @return the nomeCompleto
	 */
	public String getNomeCompleto() {
		return nomeCompleto;
	}

	/**
	 * @param nomeCompleto the nomeCompleto to set
	 */
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	/**
	 * @return the dataNasc
	 */
	public Date getDataNasc() {
		return dataNasc;
	}

	/**
	 * @param dataNasc the dataNasc to set
	 */
	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	/**
	 * @return the genero
	 */
	public String getGenero() {
		return genero;
	}

	/**
	 * @param genero the genero to set
	 */
	public void setGenero(String genero) {
		this.genero = genero;
	}
	

}
