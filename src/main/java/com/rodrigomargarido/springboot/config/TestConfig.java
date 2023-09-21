package com.rodrigomargarido.springboot.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.rodrigomargarido.springboot.entities.Category;
import com.rodrigomargarido.springboot.entities.Order;
import com.rodrigomargarido.springboot.entities.Product;
import com.rodrigomargarido.springboot.entities.User;
import com.rodrigomargarido.springboot.entities.enums.OrderStatus;
import com.rodrigomargarido.springboot.repositories.CategoryRepository;
import com.rodrigomargarido.springboot.repositories.OrderRepository;
import com.rodrigomargarido.springboot.repositories.ProductRepository;
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
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Electronics"); 
		Category cat2 = new Category(null, "Books"); 
		Category cat3 = new Category(null, "Computers"); 
		
		//Cria novos produtos
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		//Criando novos usuários
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456"); 
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456"); 
		
		//Cria novos pedidos
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1); 
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2); 
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1); 
		
		//Salva uma lista de objetos no banco de dados
		userRepository.saveAll(Arrays.asList(u1, u2)); //Cria uma lista de objetos e salva no BD
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		
	}
	
	
}
