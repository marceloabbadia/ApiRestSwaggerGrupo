package org.serratec.grupo1.projetofinal.repository;

import org.serratec.grupo1.projetofinal.model.PerfilUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PerfilUsuarioRepository extends JpaRepository<PerfilUsuario, Long> {
    @Query(value = "SELECT pu  FROM PerfilUsuario pu JOIN pu.usuario u where u.id = ?1")
    PerfilUsuario findByIdUsuario(Long idUsuario);
}
