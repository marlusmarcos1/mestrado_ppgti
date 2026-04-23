package ufrn.imd.eventos.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufrn.imd.eventos.domain.entidades.Ingresso;
import ufrn.imd.eventos.domain.entidades.dto.request.IngressoRequestDTO;
import ufrn.imd.eventos.domain.entidades.dto.response.IngressoResponseDTO;
import ufrn.imd.eventos.domain.enums.TipoIngresso;
import ufrn.imd.eventos.repository.IngressoRepository;
import ufrn.imd.eventos.service.IngressoService;

import java.util.List;

@RestController
@RequestMapping("/ingressos")
public class IngressoController {

    private final IngressoService service;
    private final IngressoRepository repository;

    public IngressoController(
            IngressoService service,
            IngressoRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<IngressoResponseDTO> criar(
            @Valid @RequestBody IngressoRequestDTO dto) {

        return ResponseEntity.ok(service.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<Ingresso>> listar() {
        return ResponseEntity.ok(repository.findAll());
    }

    // JPQL
    @GetMapping("/tipo")
    public ResponseEntity<List<Ingresso>> buscarPorTipo(@RequestParam TipoIngresso tipo) {
        return ResponseEntity.ok(repository.buscarPorTipo(tipo));
    }
}
