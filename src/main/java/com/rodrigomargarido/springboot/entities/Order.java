package com.rodrigomargarido.springboot.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rodrigomargarido.springboot.entities.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity //Define a classe como uma Entidade
@Table(name = "tb_order") //Define o nome da tabela na base de dados
public class Order implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id //Define a seguinte variavel como chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Para definir que será uma auto incremento na tabela de dados
	private Long id;
	
	//Para formatar a string do instante no jpa
	@JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;
	
	private Integer orderStatus;
	
	@ManyToOne //Muitos pra um
	@JoinColumn(name = "client_id") //Nome para a chave estrangeira
	private User client;
	
	@OneToMany(mappedBy = "id.order")
	private Set<OrderItem> items = new HashSet<>(); //Inicializa-se todas as coleções
	
	//Mapeamento de um para um com mesmo id
	//Mappeando as duas entidade para ter o mesmo id
	//Se o pedido ter codigo 5, o pagamento também vai ter codigo 5
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Payment payment;
	
	public Order() {
	}

	public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
		this.id = id;
		this.moment = moment;
		this.setOrderStatus(orderStatus); //Atribui o valor OrderStatus já convertino no var Integer
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public OrderStatus getOrderStatus() { //Converte o integer em OrderStatus
		return OrderStatus.valueOf(this.orderStatus);
	}

	public void setOrderStatus(OrderStatus orderStatus) { //Converte o OrderStatus em integer
		if( orderStatus != null)
			this.orderStatus = orderStatus.getCode();
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Set<OrderItem> getItems(){
		return items;
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
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", moment=" + moment + ", client=" + client + "]";
	}
	
	
	
}
