package ufrn.imd.eventos.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufrn.imd.eventos.domain.entidades.Organizador;
import ufrn.imd.eventos.domain.entidades.dto.request.OrganizadorRequestDTO;
import ufrn.imd.eventos.repository.OrganizadorRepository;

import java.util.List;

@RestController
@RequestMapping("/organizadores")
public class OrganizadorController {

    private final OrganizadorRepository repository;

    public OrganizadorController(OrganizadorRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Organizador> criar(
            @Valid @RequestBody OrganizadorRequestDTO dto) {

        Organizador o = new Organizador();
        o.setNome(dto.getNome());
        o.setEmail(dto.getEmail());

        return ResponseEntity.ok(repository.save(o));
    }

    @GetMapping
    public ResponseEntity<List<Organizador>> listar() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Organizador>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(repository.buscarPorNome(nome));
    }
}
