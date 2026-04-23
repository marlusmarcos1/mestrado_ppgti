package ufrn.imd.eventos.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufrn.imd.eventos.domain.entidades.Evento;
import ufrn.imd.eventos.domain.entidades.dto.request.EventoRequestDTO;
import ufrn.imd.eventos.domain.entidades.dto.response.EventoResponseDTO;
import ufrn.imd.eventos.repository.EventoRepository;
import ufrn.imd.eventos.service.EventoService;

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
    @PostMapping
    public ResponseEntity<EventoResponseDTO> criar(
            @Valid @RequestBody EventoRequestDTO dto) {
        return ResponseEntity.ok(service.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<Evento>> listar() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(
                repository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Evento não encontrado"))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody EventoRequestDTO dto) {

        Evento evento = repository.findById(id).orElseThrow();

        evento.setNome(dto.getNome());
        evento.setLocal(dto.getLocal());
        evento.setDataEvento(dto.getDataEvento());
        evento.setCapacidade(dto.getCapacidade());

        return ResponseEntity.ok(repository.save(evento));
    }

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
