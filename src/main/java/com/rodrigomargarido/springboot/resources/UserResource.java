package com.rodrigomargarido.springboot.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rodrigomargarido.springboot.entities.User;
import com.rodrigomargarido.springboot.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	//Utiliza-se um endpont de get para resgatar algum dado do BD
	//Para inserir um novo recurso, usa-se o metodo http post
	
	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity< List<User> > findAll(){
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	//A requisição poderá aceitar um Id dentro da url
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> finById(@PathVariable Long id){
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj); //Codigo http = 200
	}
	
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj){
		obj = this.service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj); //Codigo http = 201
	}
	
	@DeleteMapping(value = "/{id}")
	//Do tipo Void pois não vai retornar nenhum corpo
	public ResponseEntity<Void> delete(@PathVariable Long id){ //Pro id ser reconhecido na URL
		this.service.delete(id);
		//Uma resposta sem corpo(vazia)
		return ResponseEntity.noContent().build(); //Codigo http = 204
	}
	
}
