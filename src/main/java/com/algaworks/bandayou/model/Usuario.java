package com.algaworks.bandayou.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue
	@Column(name="id_usuario")
	private Long id_usuario;
	@NotEmpty(message="Nome obrigatório!")
	private String nome;
	
	@NotEmpty(message="Email obrigatório!")
	private String email;
	
	@NotNull(message = "Data de Nascimento é obrigatória")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE )
	
	private Date dtNasc;
	
	@NotNull(message = "Salário é obrigatório")
	@DecimalMin(value = "0.01", message = "Valor não pode ser menor que 0,01")
	@DecimalMax(value = "9999999.99", message = "Valor não pode ser maior que 9.999.999,99")
	@NumberFormat(pattern = "#,##0.00")
	private Float salario;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private Set<Festa> festas;
	
	public Long getIdUsuario() {
		return id_usuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.id_usuario = idUsuario;
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
	public Date getDtNasc() {
		return dtNasc;
	}
	public void setDtNasc(Date dtNasc) {
		this.dtNasc = dtNasc;
	}
	public Float getSalario() {
		return salario;
	}
	public void setSalario(Float salario) {
		this.salario = salario;
	}
	public Set<Festa> getFestas() {
		return festas;
	}
	public void setFestas(Set<Festa> festas) {
		this.festas = festas;
	}
	
}
