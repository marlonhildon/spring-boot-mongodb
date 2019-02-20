package com.marlonhildon.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marlonhildon.workshopmongo.domain.User;
import com.marlonhildon.workshopmongo.services.UserService;

// Para dizer que a classe é um recurso Rest, usa-se a anotação @RestController
// @RequestMapping indica o caminho do end point

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired							// Injetando o serviço
	private UserService service;
	
	@RequestMapping(method = RequestMethod.GET)		// @GetMapping também funciona
	public ResponseEntity<List<User>> findAll() {	// Sem o ResponseEntity funciona (seria public List<User> findAll())
		
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
}
