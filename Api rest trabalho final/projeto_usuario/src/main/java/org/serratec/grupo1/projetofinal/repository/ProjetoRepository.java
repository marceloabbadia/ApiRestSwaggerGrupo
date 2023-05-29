package org.serratec.grupo1.projetofinal.repository;

import org.serratec.grupo1.projetofinal.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProjetoRepository extends JpaRepository<Projeto,Long> {
    Projeto findByNomeProjeto(String nomeProjeto);

    /* 
    @Query(nativeQuery = true, value = "SELECT * FROM projeto p" +  
    " JOIN tarefa t ON (p.id = t.id_projeto)")
    List<RelatorioProjetoTarefasDto> relatorioTarefas();
    */

    @Query(value = "SELECT p FROM Projeto p" +  
    " JOIN p.tarefas t WHERE p.id = ?1 ")
    Projeto relatorioTarefas(Long idProjeto);

}