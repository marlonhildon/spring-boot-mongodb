package com.marlonhildon.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marlonhildon.workshopmongo.domain.User;
import com.marlonhildon.workshopmongo.dto.UserDTO;
import com.marlonhildon.workshopmongo.repository.UserRepository;
import com.marlonhildon.workshopmongo.services.exception.ObjectNotFoundException;

/* Esta classe de serviço serve para retornar todos os usuários (findAll() e para achar um deles pelo Id (findById()).
 * Para isso, usa-se a anotação @Service na classe. Já no objeto repo, o @Autowired é usado para injeção
 * de dependência automática do Spring. Assim, quando o Spring Data vê tal anotação nesse objeto, ele procurará a
 * definição deste (que aqui é a interface UserRepository) e instanciará o objeto automaticamente. 
 */
@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public User fromDTO(UserDTO objDTO) {
		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
	}
}
