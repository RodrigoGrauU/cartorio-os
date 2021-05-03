package com.projetos.cartorioos.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity(name = "cartorio")
public class Cartorio {
	
	@Id
	@Column(name = "id_cartorio")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String endereco;
	
	@OneToMany(mappedBy = "cartorio", targetEntity = Certidao.class, cascade = CascadeType.ALL)
	private List<Certidao> certidoes;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public List<Certidao> getCertidoes() {
		return certidoes;
	}
	
	public void setCertidoes(List<Certidao> certidoes) {
		this.certidoes = certidoes;
	}
	
	

}
