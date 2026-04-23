package ufrn.imd.eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ufrn.imd.eventos.domain.entidades.Evento;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    @Query("SELECT e FROM Evento e WHERE LOWER(e.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Evento> buscarPorNome(@Param("nome") String nome);

    @Query("SELECT e FROM Evento e JOIN FETCH e.participantes WHERE e.id = :id")
    Optional<Evento> buscarComParticipantes(@Param("id") Long id);

    @Query("SELECT e FROM Evento e JOIN FETCH e.organizador")
    List<Evento> buscarTodosComOrganizador();

    @Query(value = "SELECT * FROM eventos WHERE capacidade > :capacidade", nativeQuery = true)
    List<Evento> buscarEventosComCapacidadeMaior(@Param("capacidade") Integer capacidade);
}
