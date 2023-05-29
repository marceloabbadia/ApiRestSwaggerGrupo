package org.serratec.grupo1.projetofinal.dto.usuario;

import io.swagger.v3.oas.annotations.media.Schema;

public class LoginResponseDto {
	
	@Schema(description = "Token do usuario", required = true)
	private String token;
	
	@Schema(description = "Nome do usuario", required = true)
	private UsuarioResponseDto usuario;
	
	@Schema(description = "Data cadastro do usuario", required = true)
	private String dataCadastro;
	
	

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the usuario
	 */
	public UsuarioResponseDto getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(UsuarioResponseDto usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the dataCadastroDto
	 */
	public String getDataCadastro() {
		return dataCadastro;
	}

	/**
	 * @param dataCadastroDto the dataCadastroDto to set
	 */
	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	
	

}
