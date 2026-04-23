package ufrn.imd.eventos.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufrn.imd.eventos.domain.entidades.Participante;
import ufrn.imd.eventos.domain.entidades.dto.EventoParticipanteDTO;
import ufrn.imd.eventos.domain.entidades.dto.request.ParticipanteRequestDTO;
import ufrn.imd.eventos.repository.ParticipanteRepository;
import ufrn.imd.eventos.service.ParticipanteService;

import java.util.List;

@RestController
@RequestMapping("/participantes")
public class ParticipanteController {

    private final ParticipanteRepository repository;
    private final ParticipanteService service;

    public ParticipanteController(
            ParticipanteRepository repository,
            ParticipanteService service) {
        this.repository = repository;
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Participante> criar(
            @Valid @RequestBody ParticipanteRequestDTO dto) {

        Participante p = new Participante();
        p.setNome(dto.getNome());
        p.setEmail(dto.getEmail());

        return ResponseEntity.ok(repository.save(p));
    }

    @GetMapping
    public ResponseEntity<List<Participante>> listar() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping("/adicionar")
    public ResponseEntity<Void> adicionarNoEvento(
            @Valid @RequestBody EventoParticipanteDTO dto) {

        service.adicionarParticipante(dto);
        return ResponseEntity.ok().build();
    }
}
