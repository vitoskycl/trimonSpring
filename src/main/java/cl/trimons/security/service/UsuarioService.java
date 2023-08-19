package cl.trimons.security.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cl.trimons.security.entity.Rol;
import cl.trimons.security.entity.Usuario;
import cl.trimons.security.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService{
	private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private BCryptPasswordEncoder passwordEncoder;	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("loadUserByUsername ({})", username);
		Usuario usuario = usuarioRepository.findByUsernameAndEnable(username, true);
	    if (usuario == null) {
	        throw new UsernameNotFoundException("Usuario is not Found");
	     }
	     return new org.springframework.security.core.userdetails.User(usuario.getUsername(),
	           usuario.getPassword(),
	           mapRolesToAuthorities(usuario.getRoles()));
	}
	
	
	public Usuario save(Usuario nuevoUsuario) {		
		passwordEncoder = new BCryptPasswordEncoder();
		String encode = passwordEncoder.encode(nuevoUsuario.getPassword());
		nuevoUsuario.setPassword(encode);
		return usuarioRepository.save(nuevoUsuario); 
	}
	
	  private Collection<? extends GrantedAuthority> mapRolesToAuthorities(List<Rol> roles) {
		     return roles.stream()
		           .map(role -> new SimpleGrantedAuthority(role.getNombre()))
		           .collect(Collectors.toList());
		  }	

}
