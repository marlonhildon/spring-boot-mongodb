package com.marlonhildon.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.marlonhildon.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")	//Consulta simples com @Query e $regex
	List<Post> searchTitle(String text);
	
	List<Post> findByTitleContainingIgnoreCase(String text); // O Spring monta a consulta apenas digitando esse query method.
}
