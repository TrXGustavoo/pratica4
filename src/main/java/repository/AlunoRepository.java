package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import plataforma.pratica4.dominio.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	
}