package br.ufrn.imd.gestaofinanceiro.repository;

import br.ufrn.imd.gestaofinanceiro.modelo.entidades.Conta;
import br.ufrn.imd.gestaofinanceiro.modelo.enums.Categoria;
import br.ufrn.imd.gestaofinanceiro.modelo.enums.StatusConta;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ContaRepository {
    private Map<Long, Conta> banco = new HashMap<>();
    Long valor_id = 1L;

    public Conta salvar (Conta conta) {
        conta.setId(this.valor_id);
        this.valor_id ++;
        banco.put(conta.getId(), conta);
        return conta;
    }
    public List<Conta> findAll () {
        return new ArrayList<>(banco.values());
    }
    public Optional<Conta> findbyID(Long id) {
        return Optional.ofNullable(banco.get(id));
    }

    public void delete (Long id) {
        banco.remove(id);
    }
    public List<Conta> buscarporStatus (StatusConta status) {
        return banco.values().stream().filter( c -> c.getStatus() == status)
                .collect(Collectors.toList());
    }
    public List <Conta> buscaPorCategoria (Categoria categoria) {
        return banco.values().stream().filter( c -> c.getCategoria() == categoria).collect(Collectors.toList());
    }

}
