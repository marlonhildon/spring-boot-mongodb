package com.marlonhildon.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marlonhildon.workshopmongo.domain.User;
import com.marlonhildon.workshopmongo.repository.UserRepository;

/* Esta classe de serviço, no momento, serve para retornar todos os usuários.
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
}
