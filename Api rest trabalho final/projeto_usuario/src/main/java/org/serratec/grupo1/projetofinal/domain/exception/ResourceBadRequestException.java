package org.serratec.grupo1.projetofinal.domain.exception;

public class ResourceBadRequestException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ResourceBadRequestException (String mensagem) {
		super(mensagem);
	}

}
