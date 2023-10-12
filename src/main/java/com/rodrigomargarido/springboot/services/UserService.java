package com.rodrigomargarido.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigomargarido.springboot.entities.User;
import com.rodrigomargarido.springboot.repositories.UserRepository;

@Service //Registra sua classe como um serviço do Spring
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> optional = repository.findById(id);
		return optional.get();
	}
	
	//Metodo para inserir no BD um usuario
	//Retorna o usuario salvo
	public User insert(User obj) {
		return this.repository.save(obj); //O save por padrão ha retorna o obj salvo
	}
}
