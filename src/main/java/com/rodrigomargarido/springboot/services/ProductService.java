package com.rodrigomargarido.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigomargarido.springboot.entities.Product;
import com.rodrigomargarido.springboot.repositories.ProductRepository;

@Service //Registra sua classe como um serviço do Spring
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	public List<Product> findAll(){
		return repository.findAll();
	}
	
	public Product findById(Long id) {
		Optional<Product> optional = repository.findById(id);
		return optional.get();
	}
}
