package com.algaworks.bandayou.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Banda {	
	@Id
	@GeneratedValue
	@Column(name="id_banda")
	private Long idBanda;
	
	@NotEmpty(message="Nome da Banda obrigatório!")
	private String nome;
	
	@NotEmpty(message="Email obrigatório!")
	private String email;
	
	@NotEmpty(message="Estilo da Banda obrigatório!")
	private String estilo;
	
	@OneToMany(mappedBy = "banda", cascade = CascadeType.ALL)
	private Set<Musica> musicas;

	public Long getIdBanda() {
		return idBanda;
	}

	public void setIdBanda(Long idBanda) {
		this.idBanda = idBanda;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEstilo() {
		return estilo;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}

	public Set<Musica> getMusicas() {
		return musicas;
	}

	public void setMusicas(Set<Musica> musicas) {
		this.musicas = musicas;
	}
}
