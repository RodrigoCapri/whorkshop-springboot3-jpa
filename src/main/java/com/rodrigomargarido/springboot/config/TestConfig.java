package com.rodrigomargarido.springboot.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.rodrigomargarido.springboot.entities.Order;
import com.rodrigomargarido.springboot.entities.User;
import com.rodrigomargarido.springboot.repositories.OrderRepository;
import com.rodrigomargarido.springboot.repositories.UserRepository;

//Classe de configuração
@Configuration
//Configuração de profile teste
@Profile("test")
public class TestConfig implements CommandLineRunner{ //Implementa CommandLineRunner para ser iniciado na aplicação

	@Autowired //Resolve a dependencia e associar uma instancia de userRepository aqui dentro
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456"); 
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456"); 
		
		//Cria novos pedidos
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), u1); 
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), u2); 
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), u1); 
		
		//Salva uma lista de objetos no banco de dados
		userRepository.saveAll(Arrays.asList(u1, u2)); //Cria uma lista de objetos e salva no BD
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		
	}
	
	
}
