package cl.trimons.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.trimons.security.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Usuario findByUsernameAndEnable(String username, boolean enable);
}
