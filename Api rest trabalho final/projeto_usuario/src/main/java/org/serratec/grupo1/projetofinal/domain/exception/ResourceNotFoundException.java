package org.serratec.grupo1.projetofinal.domain.exception;

public class ResourceNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException (String mensagem) {
		super(mensagem);
	}

}
