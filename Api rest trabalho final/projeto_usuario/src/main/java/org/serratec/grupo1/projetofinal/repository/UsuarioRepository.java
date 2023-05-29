package org.serratec.grupo1.projetofinal.repository;

import java.util.Optional;

import org.serratec.grupo1.projetofinal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{

	Optional<Usuario> findByEmail(String email);

	
	@Query(value = "SELECT u FROM Usuario u" +  
    " JOIN u.tarefas t WHERE u.id = ?1 ")
    Usuario usuarioTarefas(Long idUsuario);
	
}



