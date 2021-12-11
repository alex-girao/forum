package br.com.alexgirao.forum.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Classe de representa o perfil do usuario
 * para o spring
 */
@Data
@EqualsAndHashCode
@Entity
@NoArgsConstructor
public class Perfil implements GrantedAuthority {
	
	private static final long serialVersionUID = -2083461039786443653L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;

	@Override
	public String getAuthority() {
		return nome;
	}

}
