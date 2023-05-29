package org.serratec.grupo1.projetofinal.model;

import java.util.List;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table
public class Projeto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column (name ="id_projeto")
	private Long id;
	
	@Column(name="nome_projeto")
	@NotBlank(message = "Digite um nome válido")
	@Schema(description = "Nome do Projeto", required = true)
	private String nomeProjeto;
	
	@Column(name="descricao_projeto", length = 200)
	@NotBlank(message = "Digite uma descrição válida")
	@Schema(description = "Descrição do Projeto", required = true)
	private String descProjeto;
	
	@OneToMany(mappedBy ="projeto")
	private List<Tarefa> tarefas;	

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nomeProjeto
	 */
	public String getNomeProjeto() {
		return nomeProjeto;
	}

	/**
	 * @param nomeProjeto the nomeProjeto to set
	 */
	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}

	/**
	 * @return the descProjeto
	 */
	public String getDescProjeto() {
		return descProjeto;
	}

	/**
	 * @param descProjeto the descProjeto to set
	 */
	public void setDescProjeto(String descProjeto) {
		this.descProjeto = descProjeto;
	}

	/**
	 * @return the tarefas
	 */
	

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Projeto other = (Projeto) obj;
		return Objects.equals(id, other.id);
	}

}
