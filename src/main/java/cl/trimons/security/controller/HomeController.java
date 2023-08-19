package cl.trimons.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cl.trimons.security.entity.Usuario;
import cl.trimons.security.service.UsuarioService;

@RestController
public class HomeController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/home")
	public ResponseEntity<String> home(){
		return new ResponseEntity<String>("Bienvenido", HttpStatus.OK);
	}
	
	@PostMapping("/usuario")
	public ResponseEntity<Usuario> save(@RequestBody Usuario nuevoUsuario) {
		return new ResponseEntity<Usuario>(usuarioService.save(nuevoUsuario), HttpStatus.CREATED);
	}
}
