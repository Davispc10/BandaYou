package com.algaworks.bandayou.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Musica {
	@Id
	@GeneratedValue
	private Long id_musica;
	
	@ManyToOne
	@JoinColumn(name = "id_banda")
	@NotNull(message = "banda obrigatório!")
	private Banda banda;
	
	@NotEmpty(message = "Musica obrigatória!")
	private String nome;
	
	@NotEmpty(message = "Compositor obrigatória!")
	private String compositor;

	public Long getIdMusica() {
		return id_musica;
	}

	public void setIdMusica(Long idMusica) {
		this.id_musica = idMusica;
	}

	public Banda getBanda() {
		return banda;
	}

	public void setBanda(Banda banda) {
		this.banda = banda;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCompositor() {
		return compositor;
	}

	public void setCompositor(String compositor) {
		this.compositor = compositor;
	}
}
