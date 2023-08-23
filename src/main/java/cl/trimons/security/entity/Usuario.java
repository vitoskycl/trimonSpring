package cl.trimons.security.entity;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
 
@Entity
@Getter
@Setter
@Transactional
public class Usuario implements UserDetails {

    private static final long serialVersionUID = -495589395260720686L;

	@Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(length = 500)
    private String nombre;

    @Column(nullable = false)
    private Boolean enable;

    @OneToMany(mappedBy = "usuario")
    private Set<UsuarioInRol> usuarioUsuarioInRols;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "UsuarioInRol",
       joinColumns = @JoinColumn(name = "idUsuario"),
       inverseJoinColumns = @JoinColumn(name = "idRol"))
    private List<Rol> roles;
    
//	public String toString(){
//		return ToStringBuilder.reflectionToString(this);
//	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	     return roles.stream()
		           .map(role -> new SimpleGrantedAuthority(role.getNombre()))
		           .collect(Collectors.toList());
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}  
}