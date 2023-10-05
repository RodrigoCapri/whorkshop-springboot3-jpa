package com.rodrigomargarido.springboot.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigomargarido.springboot.entities.OrderItem;
import com.rodrigomargarido.springboot.services.OrderItemService;

@RestController
@RequestMapping(value = "/order_item")
public class OrderItemResource {
	
	@Autowired
	private OrderItemService service;
	
	@GetMapping
	public ResponseEntity< List<OrderItem> > findAll(){
		List<OrderItem> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	//A requisição poderá aceitar um Id dentro da url
	@GetMapping(value = "/{id}")
	public ResponseEntity<OrderItem> finById(@PathVariable Long id){
		OrderItem obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
}
