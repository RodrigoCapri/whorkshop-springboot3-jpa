package com.rodrigomargarido.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigomargarido.springboot.entities.OrderItem;
import com.rodrigomargarido.springboot.repositories.OrderItemRepository;

@Service //Registra sua classe como um serviço do Spring
public class OrderItemService {

	@Autowired
	private OrderItemRepository repository;
	
	public List<OrderItem> findAll(){
		return repository.findAll();
	}
	
	public OrderItem findById(Long id) {
		Optional<OrderItem> optional = repository.findById(id);
		return optional.get();
	}
}
