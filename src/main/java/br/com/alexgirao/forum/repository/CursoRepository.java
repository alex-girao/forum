package br.com.alexgirao.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alexgirao.forum.modelo.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{

	Curso findByNome(String nomeCurso);

}
