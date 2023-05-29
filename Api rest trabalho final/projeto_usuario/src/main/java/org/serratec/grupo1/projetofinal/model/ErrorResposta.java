package org.serratec.grupo1.projetofinal.model;


public class ErrorResposta {
	
	private String dataHora;
	
	private int status;
	
	private String titulo;
	
	private String mensagem;
	

	public ErrorResposta(String dataHora, int status, String titulo, String mensagem) {
		super();
		this.dataHora = dataHora;
		this.status = status;
		this.titulo = titulo;
		this.mensagem = mensagem;
	}

	/**
	 * @return the dataHora
	 */
	public String getDataHora() {
		return dataHora;
	}

	/**
	 * @param dataHora the dataHora to set
	 */
	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
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
	 * @return the mensagem
	 */
	public String getMensagem() {
		return mensagem;
	}

	/**
	 * @param mensagem the mensagem to set
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
	
	

}
