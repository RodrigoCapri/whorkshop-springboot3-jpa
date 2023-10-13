package com.rodrigomargarido.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.rodrigomargarido.springboot.entities.User;
import com.rodrigomargarido.springboot.repositories.UserRepository;
import com.rodrigomargarido.springboot.services.exceptions.DatabaseException;
import com.rodrigomargarido.springboot.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service //Registra sua classe como um serviço do Spring
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> optional = repository.findById(id);
		
		//Tenta dar um get, se não houver o usuario, lança uma exceção
		return optional.orElseThrow( () -> new ResourceNotFoundException(id) ); 
	}
	
	//Metodo para inserir no BD um usuario
	//Retorna o usuario salvo
	public User insert(User obj) {
		return this.repository.save(obj); //O save por padrão ha retorna o obj salvo
	}
	
	public void delete(Long id) {
		try {
			this.repository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}catch(DataIntegrityViolationException e) { //Violação de integridade de dados
			throw new DatabaseException(e.getMessage()); 
		}
		
	}
	
	public User update(Long id, User obj) {
		try {
			User entity  = repository.getReferenceById(id); //Retorna uma referencia de usuario ja existente no id indicado
			updateDate(entity, obj);
			return this.repository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateDate(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
	
	
}
