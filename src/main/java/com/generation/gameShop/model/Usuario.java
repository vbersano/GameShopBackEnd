package com.generation.gameShop.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Entidade que dita como sera construida a base de dados, e como serão salvos os usuarios
@Entity
@Table (name = "tb_usuario")
public class Usuario {
	
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	@NotBlank (message = "O campo nome é obrigatório")
	@Size(min = 5, max = 100, message = "O nome deve conter de 5 a 100 caracteres")
	private String nome;

	@Email
	@NotBlank (message = "O campo usuário é obrigatório")
	private String usuario;
	
	@NotBlank (message = "O campo senha é obrigatório")
	@Size(min = 8, message = "A senha deve conter no mínimo 8 caracteres")
	private String senha;
	
	private String foto;
	
	@NotNull
	private String tipoUsuario;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties ("usuario")
	private List <Produto>produto;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}
	
	
	
}

