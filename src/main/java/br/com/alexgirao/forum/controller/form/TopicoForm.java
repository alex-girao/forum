package br.com.alexgirao.forum.controller.form;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.alexgirao.forum.modelo.Curso;
import br.com.alexgirao.forum.modelo.Topico;
import br.com.alexgirao.forum.repository.CursoRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicoForm {
	
	@NotEmpty @Length(min = 5)
	private String titulo;
	@NotEmpty @Length(min = 10)
	private String mensagem;
	//TODO alterar para id no futuro
	@NotEmpty
	private String nomeCurso;
	
	public Topico converter(CursoRepository cursoRepository) {
		Curso curso = cursoRepository.findByNome(nomeCurso);
		return new Topico(titulo, mensagem, curso);
	}
	
	

}
