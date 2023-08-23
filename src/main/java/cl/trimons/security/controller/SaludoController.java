package cl.trimons.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaludoController {

	
	@GetMapping("/saludo")
	public ResponseEntity<String> saludo(){
		return new ResponseEntity<String>("Hola este es un Saludo", HttpStatus.OK);
	}
}
