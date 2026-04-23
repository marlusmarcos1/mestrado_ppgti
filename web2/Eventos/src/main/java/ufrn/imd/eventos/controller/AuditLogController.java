package ufrn.imd.eventos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ufrn.imd.eventos.domain.mongo.AuditLog;
import ufrn.imd.eventos.repository.AuditLogRepository;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class AuditLogController {

    private final AuditLogRepository repository;

    public AuditLogController(AuditLogRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<AuditLog>> listar() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/acao")
    public ResponseEntity<List<AuditLog>> buscarPorAcao(@RequestParam String acao) {
        return ResponseEntity.ok(repository.findByAcao(acao));
    }
}
