package ufrn.imd.eventos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ufrn.imd.eventos.domain.mongo.AuditLog;

import java.util.List;

@Repository
public interface AuditLogRepository extends MongoRepository<AuditLog, String> {

    List<AuditLog> findByAcao(String acao);
}
