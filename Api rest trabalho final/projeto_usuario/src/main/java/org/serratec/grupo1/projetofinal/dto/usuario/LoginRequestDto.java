package org.serratec.grupo1.projetofinal.dto.usuario;

import io.swagger.v3.oas.annotations.media.Schema;

public class LoginRequestDto {
	
	@Schema(description = "Login do usuario", required = true)
	private String login;
	
	@Schema(description = "Senha do usuario", required = true)
	private String senha;

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
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

	

}
