package br.com.alexgirao.forum.controller.form;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.alexgirao.forum.modelo.Topico;
import br.com.alexgirao.forum.repository.TopicoRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizacaoTopicoForm {
	
	@NotEmpty @Length(min = 5)
	private String titulo;
	@NotEmpty @Length(min = 10)
	private String mensagem;
	
	public Topico atualizar(Long id, TopicoRepository topicoRepository) {
		Topico topico = topicoRepository.getById(id);
		topico.setTitulo(titulo);
		topico.setMensagem(mensagem);
		return topico;
	}

}
