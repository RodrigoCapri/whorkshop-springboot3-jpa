package com.rodrigomargarido.springboot.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigomargarido.springboot.entities.Order;
import com.rodrigomargarido.springboot.services.OrderService;

@RestController
@RequestMapping(value = "/orders") //Caminho do recurso para acessar pela url
public class OrderResource {
	
	@Autowired
	private OrderService service;
	
	@GetMapping
	public ResponseEntity< List<Order> > findAll(){
		List<Order> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	//A requisição poderá aceitar um Id dentro da url
	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> finById(@PathVariable Long id){
		Order obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
}
