package br.com.alexgirao.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alexgirao.forum.controller.dto.TopicoDto;
import br.com.alexgirao.forum.modelo.Topico;
import br.com.alexgirao.forum.repository.TopicoRepository;

@RestController
public class TopicosController {
	
	@Autowired
	private TopicoRepository topicoRepository;
	
	@RequestMapping("/topicos")
	public List<TopicoDto> lista(String nomeCurso) {
		List<Topico> topicos = nomeCurso == null? 
			topicoRepository.findAll() : topicoRepository.findByCursoNome(nomeCurso);
		return TopicoDto.converter(topicos);
	}

}
