package com.rodrigomargarido.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rodrigomargarido.springboot.entities.OrderItem;

//Não precisa implementar esta interface pois, o jpa ja possui uma implementação generica
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

	
	
}
