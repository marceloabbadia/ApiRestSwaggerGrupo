package org.serratec.grupo1.projetofinal.dto.perfilusuario;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

public class PerfilUsuarioResponseDto {
	
	@Schema(description = "Nome completo do usuario", required = true)
	private String nomeCompleto;
	
	@Schema(description = "Data de nascimento do usuario", required = true)
	private Date dataNasc;
	
	@Schema(description = "Genero", required = true)
	private String genero;

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
