package com.marlonhildon.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marlonhildon.workshopmongo.domain.User;

// Para dizer que a classe é um recurso Rest, usa-se a anotação @RestController
// @RequestMapping indica o caminho do end point

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@RequestMapping(method = RequestMethod.GET)		// @GetMapping também funciona
	public ResponseEntity<List<User>> findAll() {	// Sem o ResponseEntity funciona, mas é uma boa prática usar (é sobre o cabeç. rest)
		User maria = new User("1", "Maria Brown", "maria@gmail.com");
		User alex = new User("2", "Alex Green", "alex@gmail.com");
		List<User> list = new ArrayList<>();
		list.addAll(Arrays.asList(maria, alex));
		return ResponseEntity.ok().body(list);
	}
}
