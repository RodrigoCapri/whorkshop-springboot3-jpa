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
	
	public void delete(Long id) {
		this.repository.deleteById(id);
	}
	
	public User update(Long id, User obj) {
		User entity  = repository.getReferenceById(id); //Retorna uma referencia de usuario ja existente no id indicado
		updateDate(entity, obj);
		return this.repository.save(entity);
	}

	private void updateDate(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
	
	
}
