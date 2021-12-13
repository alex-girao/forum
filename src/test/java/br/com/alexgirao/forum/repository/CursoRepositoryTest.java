package br.com.alexgirao.forum.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.alexgirao.forum.modelo.Curso;

@RunWith(SpringRunner.class)
@DataJpaTest
// Nao utilizar o banco dedados padrao
@AutoConfigureTestDatabase(replace = Replace.NONE)
// carrega o profile de test
@ActiveProfiles("test")
public class CursoRepositoryTest {
	
	// o spring permite a injecao de dependencia em cenarios de testes
	@Autowired
	private CursoRepository repository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	void deveriaCarregarUmCursoAoBuscarPeloSeuNome() {
		String nomeCurso = "HTML 5";
		
		Curso html5 = new Curso();
		html5.setNome(nomeCurso);
		html5.setCategoria("Programacao");
		entityManager.persist(html5);
		
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
