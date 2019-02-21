package com.marlonhildon.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.marlonhildon.workshopmongo.domain.Post;
import com.marlonhildon.workshopmongo.domain.User;
import com.marlonhildon.workshopmongo.dto.UserDTO;
import com.marlonhildon.workshopmongo.services.UserService;

// Para dizer que a classe é um recurso Rest, usa-se a anotação @RestController
// @RequestMapping indica o caminho do end point

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired							// Injetando o serviço
	private UserService service;
	
	@RequestMapping(method = RequestMethod.GET)		// @GetMapping também funciona
	public ResponseEntity<List<UserDTO>> findAll() {	// Sem o ResponseEntity funciona (seria public List<UserDTO> findAll())
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList()); // Converte objetos para DTO
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET) // Este método terá o caminho /users/{valor da id}
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {	// A anotação serve para a var. casar com a id da url
		User obj = service.findById(id);
		
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	@RequestMapping(method = RequestMethod.POST) // @PostMapping também funciona
	public ResponseEntity<Void> findById(@RequestBody UserDTO objDTO) {	//A anotação serve para o objeto ser aceito pelo endpoint.
		User obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();	//Pega o endereço do novo objeto criado
		return ResponseEntity.created(uri).build();		// O created retorna o código 201 com o cabeçalho contendo a localização do novo recurso criado
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id) {	// A anotação serve para a var. casar com a id da url
		service.delete(id);		
		return ResponseEntity.noContent().build();	// Retorna o código 204
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT) // @
	public ResponseEntity<Void> update(@RequestBody UserDTO objDTO, @PathVariable String id) {	
		User obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}/posts", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
		User obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj.getPosts());
	}
}
