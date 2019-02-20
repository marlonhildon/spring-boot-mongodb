package com.marlonhildon.workshopmongo.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.marlonhildon.workshopmongo.services.exception.ObjectNotFoundException;

/* Classe responsável por retornar erros adequadamente. Se o usuário entrar num endereço inválido, por exemplo,
 * comumente é retornado o erro 500, quando na verdade o erro deveria ser 404. Esta classe conserta isso.
*/

@ControllerAdvice							// Anota a classe que trata possíveis erros nas requisições
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)	// Anotação: quando ocorrer a exceção entre parênteses, trate-a da seguinte forma:
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(
				System.currentTimeMillis(),
				status.value(),
				"Não encontrado",
				e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
