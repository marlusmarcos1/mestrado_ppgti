package ufrn.imd.eventos.domain.mongo;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "audit_logs")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class AuditLog {

    @Id
    private String id;
    private String acao;
    private Long recursoId;
    private LocalDateTime dataHora;
    private String descricao;
}
