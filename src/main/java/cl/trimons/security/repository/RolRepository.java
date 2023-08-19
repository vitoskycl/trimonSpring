package cl.trimons.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.trimons.security.entity.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer> {

}
