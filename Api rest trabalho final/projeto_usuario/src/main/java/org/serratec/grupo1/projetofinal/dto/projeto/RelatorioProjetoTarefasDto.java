package org.serratec.grupo1.projetofinal.dto.projeto;


import java.util.List;

import org.serratec.grupo1.projetofinal.model.Projeto;
import org.serratec.grupo1.projetofinal.model.Tarefa;

import io.swagger.v3.oas.annotations.media.Schema;

public class RelatorioProjetoTarefasDto {
    
    @Schema(description = "Nome do Projeto", required = true)
    private Projeto projeto;

    @Schema(description = "Tarefas do Projeto", required = true)
    private List<Tarefa> tarefa;

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public List<Tarefa> getTarefa() {
        return tarefa;
    }

    public void setTarefa(List<Tarefa> tarefa) {
        this.tarefa = tarefa;
    }

    

}
