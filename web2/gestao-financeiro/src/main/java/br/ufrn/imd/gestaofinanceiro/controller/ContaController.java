package br.ufrn.imd.gestaofinanceiro.controller;

import br.ufrn.imd.gestaofinanceiro.modelo.dtos.ContaResponseDTO;
import br.ufrn.imd.gestaofinanceiro.modelo.dtos.ContaResquestDTO;
import br.ufrn.imd.gestaofinanceiro.modelo.entidades.Conta;
import br.ufrn.imd.gestaofinanceiro.service.ContaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/contas")
public class ContaController {

    @Autowired
    @Qualifier("contaServiceCreditoImpl")
    private ContaService contaService;


    @PostMapping
    public ResponseEntity<ContaResponseDTO> criar(@RequestBody @Valid ContaResquestDTO request) {
        Conta conta = contaService.criar(request);
        return new ResponseEntity<>(new ContaResponseDTO(conta), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ContaResponseDTO>> listar() {
        List<ContaResponseDTO> dtos = contaService.listar().stream()
                .map(ContaResponseDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaResponseDTO> buscarPorId(@PathVariable Long id) {
        try {
            Conta conta = contaService.buscarPorId(id);
            return ResponseEntity.ok(new ContaResponseDTO(conta));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/pagar")
    public ResponseEntity<?> pagar(@PathVariable Long id) {
        try {
            Conta contaPaga = contaService.pagar(id);
            return ResponseEntity.ok(new ContaResponseDTO(contaPaga));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            contaService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
