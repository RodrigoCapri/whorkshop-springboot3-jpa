package com.rodrigomargarido.springboot.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity //Define a classe como uma Entidade
@Table(name = "tb_category") //Define o nome da tabela na base de dados
public class Category implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id //Define a seguinte variavel como chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Para definir que será uma auto incremento na tabela de dados
	private Long id;
	private String category;
	
	@Transient //impede o  jpa traduzir esta linha de codigo
	private Set<Product> products = new HashSet<>();
	
	public Category() {	
	}
	
	public Category(Long id, String category) {
		this.id = id;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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
		Category other = (Category) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", category=" + category + "]";
	}

	public Set<Product> getProducts() {
		return products;
	}
	
}
