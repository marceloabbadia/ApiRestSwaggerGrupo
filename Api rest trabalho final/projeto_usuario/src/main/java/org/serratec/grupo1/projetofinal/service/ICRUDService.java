package org.serratec.grupo1.projetofinal.service;

import java.util.List;

public interface ICRUDService <Req , Res>{
	
	List<Res> obterTodos();
	Res obterPorId(Long id);
	Res cadastrar (Req dto);
	Res atualizar (Long id, Req dto);
	void deletar(Long id);
	
	
	

}
