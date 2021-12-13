package br.com.alexgirao.forum.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.alexgirao.forum.modelo.Curso;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CursoRepositoryTest {
	
	// o spring permite a injecao de dependencia
	@Autowired
	private CursoRepository repository;
	
	@Test
	void deveriaCarregarUmCursoAoBuscarPeloSeuNome() {
		String nomeCurso = "HTML 5";
		Curso curso = repository.findByNome(nomeCurso);
		Assert.assertNotNull(curso);
		Assert.assertEquals(nomeCurso, curso.getNome());
	}
	
	@Test
	void naoDeveriaCarregarUmCursoCujoNomeNaoEstariaCadastrado() {
		String nomeCurso = "JPA";
		Curso curso = repository.findByNome(nomeCurso);
		Assert.assertNull(curso);
	}

}
