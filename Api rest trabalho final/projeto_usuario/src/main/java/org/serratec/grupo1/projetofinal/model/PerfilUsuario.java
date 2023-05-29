package org.serratec.grupo1.projetofinal.model;

import java.util.Date;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;



@Entity
@Table
public class PerfilUsuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_PerfilUsuario")
	private Long id;
	
	@Column(name="nome_completo")
	@Schema(description = "Nome do usuario", required = true)
	@NotBlank(message = "Digite um nome válido")
	protected String nomeCompleto;
	
	@Past(message = "Digite uma data válida")
	@Column(name="data_nascimento")
	@Schema(description = "Data de Nascimento", required = true)
	protected Date dataNasc;
	
	@Size(min = 1, max = 1, message = "Digite um caracter")
	@Column(name="genero")
	@Pattern(regexp = "^[a-zA-Z]{1}$")
	@Schema(description = "Genero", required = true)
	protected String genero;
	
	@OneToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	
	
	
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	
	public Date getDataNasc() {
		return dataNasc;
	}
	
	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

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
		PerfilUsuario other = (PerfilUsuario) obj;
		return Objects.equals(id, other.id);
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
