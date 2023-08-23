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
import jakarta.annotation.security.RolesAllowed;

@RestController
public class HomeController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/home")
	@RolesAllowed("ADMIN")
	public ResponseEntity<String> home(){
		return new ResponseEntity<String>("Bienvenido a mi hogar", HttpStatus.OK);
	}
	
	@GetMapping("/casa")
	@RolesAllowed({"USER", "EDITOR"})
	public ResponseEntity<String> casa(){
		return new ResponseEntity<String>("Bienvenido a mi casa", HttpStatus.OK);
	}	
	
	@PostMapping("/usuario")
	public ResponseEntity<Usuario> save(@RequestBody Usuario nuevoUsuario) {
		return new ResponseEntity<Usuario>(usuarioService.save(nuevoUsuario), HttpStatus.CREATED);
	}
}
