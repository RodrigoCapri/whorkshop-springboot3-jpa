package com.rodrigomargarido.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigomargarido.springboot.entities.Order;
import com.rodrigomargarido.springboot.repositories.OrderRepository;

@Service //Registra sua classe como um serviço do Spring
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	public List<Order> findAll(){
		return repository.findAll();
	}
	
	public Order findById(Long id) {
		Optional<Order> optional = repository.findById(id);
		return optional.get();
	}
}
