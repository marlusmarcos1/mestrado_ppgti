package ufrn.imd.eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ufrn.imd.eventos.domain.entidades.Participante;

import java.util.Optional;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Long> {

    @Query("SELECT p FROM Participante p WHERE p.email = :email")
    Optional<Participante> buscarPorEmail(@Param("email") String email);
}
