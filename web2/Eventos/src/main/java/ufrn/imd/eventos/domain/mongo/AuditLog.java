package ufrn.imd.eventos.domain.mongo;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "audit_logs")
@AllArgsConstructor
@Getter
@Setter
public class AuditLog {

    @Id
    private String id;
    private String acao;
    private Long recursoId;
    private LocalDateTime dataHora;
    private String descricao;
}
