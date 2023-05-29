package org.serratec.grupo1.projetofinal.dto.usuario;


import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;


public class UsuarioRequestDto {
	
	@Schema(description = "Nome do usuario", required = true)
	private String nome;
	
	@Schema(description = "Email do usuario", required = true)
	private String email;
	
	@Schema(description = "Senha do usuario", required = true)
	private String senha;
	
	@Schema(description = "Data de cadastro do usuario", required = true)
	private Date dataCadastro;
	
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
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
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
		this.dataCadastro = dataCadastro;
	}
	
	
}
