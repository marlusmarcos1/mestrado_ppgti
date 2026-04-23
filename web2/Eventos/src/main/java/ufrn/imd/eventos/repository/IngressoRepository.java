package ufrn.imd.eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ufrn.imd.eventos.domain.entidades.Ingresso;
import ufrn.imd.eventos.domain.enums.TipoIngresso;

import java.util.List;

@Repository
public interface IngressoRepository extends JpaRepository<Ingresso, Long> {

    @Query("SELECT i FROM Ingresso i WHERE i.tipo = :tipo")
    List<Ingresso> buscarPorTipo(@Param("tipo") TipoIngresso tipo);

}
