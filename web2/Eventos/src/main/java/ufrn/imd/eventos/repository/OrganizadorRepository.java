package ufrn.imd.eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ufrn.imd.eventos.domain.entidades.Organizador;

import java.util.List;

@Repository
public interface OrganizadorRepository extends JpaRepository<Organizador, Long> {

    @Query("SELECT o FROM Organizador o WHERE o.nome LIKE %:nome%")
    List<Organizador> buscarPorNome(@Param("nome") String nome);
}
