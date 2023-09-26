package com.rodrigomargarido.springboot.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity //Define a classe como uma Entidade
@Table(name = "tb_product") //Define o nome da tabela na base de dados
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id //Define a seguinte variavel como chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Para definir que será uma auto incremento na tabela de dados
	private Integer id;
	private String name;
	private String description;
	private Double price;
	private String imgUrl;
	
	//Usa a coleção Set
	//Para garantir que não vou ter mais de um produto da mesma categoria
	@ManyToMany //Numa relação muito para muitos, cria-se uma tabela associativa
	@JoinTable(name = "tb_product_category", //Criando a tabela associativa
		joinColumns = @JoinColumn(name = "product_id"), //Criando a chave estrangeira
		inverseJoinColumns = @JoinColumn(name = "category_id") //Definir qual a chave estrangeira da outra entidade
	)
	private Set<Category> categories = new HashSet<>();
	
	public Product() {
	}

	public Product(Integer id, String name, String description, Double price, String imgUrl) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
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
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", imgUrl=" + imgUrl + "]";
	}

	public Set<Category> getCategories() {
		return categories;
	}
	
}
