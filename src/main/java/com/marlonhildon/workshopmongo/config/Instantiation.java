package com.marlonhildon.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.marlonhildon.workshopmongo.domain.User;
import com.marlonhildon.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();										// Limpa todas as entidades cadastradas no repositório
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");	// Definindo as entidades
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));		// Salvando os objetos no repositório
	}
}
