package cl.trimons.security.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Transactional
public class Rol {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRol;

    @Column(nullable = false, length = 45)
    private String nombre;

    @OneToMany(mappedBy = "rol")
    private Set<UsuarioInRol> rolUsuarioInRols;

    
    
	public Rol(String nombre) {
		this.nombre = nombre;
	}



	public Rol() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Rol(Integer idRol, String nombre) {
		super();
		this.idRol = idRol;
		this.nombre = nombre;
	}

    
}