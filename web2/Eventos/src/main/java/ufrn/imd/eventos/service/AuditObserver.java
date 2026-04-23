package ufrn.imd.eventos.service;

import org.springframework.stereotype.Component;
import ufrn.imd.eventos.domain.entidades.Evento;
import ufrn.imd.eventos.domain.mongo.AuditLog;
import ufrn.imd.eventos.repository.AuditLogRepository;

import java.time.LocalDateTime;

@Component
public class AuditObserver implements EventoObserver {

    private final AuditLogRepository repository;

    public AuditObserver(AuditLogRepository repository) {
        this.repository = repository;
    }

    @Override
    public void onEventoCriado(Evento evento) {

        AuditLog log = new AuditLog();
        log.setAcao("CRIACAO_EVENTO");
        log.setRecursoId(evento.getId());
        log.setDataHora(LocalDateTime.now());
        log.setDescricao("Evento criado: " + evento.getNome());

        repository.save(log);
    }
}
