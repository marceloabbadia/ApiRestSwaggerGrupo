package org.serratec.grupo1.projetofinal.dto.usuario;


import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;


public class UsuarioResponseDto {
	
	@Schema(description ="Identificação do usuario", required = true)
	private Long id;
	
	@Schema(description = "Nome do usuario", required = true)
	private String nome;
	
	@Schema(description = "Email do usuario", required = true)
	private String email;
	
	@Schema(description = "Data do cadastro do usuario", required = true)
	private Date dataCadastro;
	
	
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
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the dataCadastro
	 */
	public Date getDataCadastro() {
		return dataCadastro;
	}

	/**
	 * @param dataCadastro the dataCadastro to set
	 */
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro =  dataCadastro;
	}


}
