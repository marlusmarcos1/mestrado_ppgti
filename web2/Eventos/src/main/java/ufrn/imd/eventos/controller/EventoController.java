package ufrn.imd.eventos.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufrn.imd.eventos.domain.entidades.Evento;
import ufrn.imd.eventos.domain.entidades.dto.request.EventoRequestDTO;
import ufrn.imd.eventos.domain.entidades.dto.response.EventoResponseDTO;
import ufrn.imd.eventos.mapper.EventoMapper;
import ufrn.imd.eventos.repository.EventoRepository;
import ufrn.imd.eventos.service.EventoService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EventoService service;
    private final EventoRepository repository;

    public EventoController(EventoService service, EventoRepository repository) {
        this.service = service;
        this.repository = repository;
    }
    @GetMapping("/info")
    public ResponseEntity<String> info() {
        return ResponseEntity.ok("API de Gestão de Eventos - Público");
    }

    @PreAuthorize("hasRole('MASTER')")
    @PostMapping
    public ResponseEntity<EventoResponseDTO> criar(
            @Valid @RequestBody EventoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @PreAuthorize("hasAnyRole('MASTER', 'CONTRIBUTOR', 'AUDITOR')")
    @GetMapping
    public ResponseEntity<List<EventoResponseDTO>> listar() {
        return ResponseEntity.ok(EventoMapper.toDTOList(repository.findAll()));
    }

    @PreAuthorize("hasAnyRole('MASTER', 'CONTRIBUTOR', 'AUDITOR')")
    @GetMapping("/{id}")
    public ResponseEntity<EventoResponseDTO> buscarPorId(@PathVariable Long id) {
        Evento evento = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento não encontrado"));
        return ResponseEntity.ok(EventoMapper.toDTO(evento));
    }

    @PreAuthorize("hasAnyRole('MASTER', 'CONTRIBUTOR')")
    @PutMapping("/{id}")
    public ResponseEntity<EventoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody EventoRequestDTO dto) {

        Evento evento = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento não encontrado"));

        evento.setNome(dto.getNome());
        evento.setLocal(dto.getLocal());
        evento.setDataEvento(dto.getDataEvento());
        evento.setCapacidade(dto.getCapacidade());

        return ResponseEntity.ok(EventoMapper.toDTO(repository.save(evento)));
    }

    @PreAuthorize("hasRole('MASTER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Evento>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(repository.buscarPorNome(nome));
    }

    @GetMapping("/{id}/participantes")
    public ResponseEntity<Evento> buscarComParticipantes(@PathVariable Long id) {
        return ResponseEntity.ok(
                repository.buscarComParticipantes(id)
                        .orElseThrow()
        );
    }
    @GetMapping("/capacidade")
    public ResponseEntity<List<Evento>> buscarPorCapacidade(@RequestParam Integer capacidade) {
        return ResponseEntity.ok(
                repository.buscarEventosComCapacidadeMaior(capacidade)
        );
    }
}
