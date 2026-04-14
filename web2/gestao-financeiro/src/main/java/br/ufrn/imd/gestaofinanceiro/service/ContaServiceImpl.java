package br.ufrn.imd.gestaofinanceiro.service;

import br.ufrn.imd.gestaofinanceiro.modelo.dtos.ContaResquestDTO;
import br.ufrn.imd.gestaofinanceiro.modelo.entidades.Conta;
import br.ufrn.imd.gestaofinanceiro.repository.ContaRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
public class ContaServiceImpl implements  ContaService {

    private final ContaRepository contaRepository;

    @Override
    public Conta criar(ContaResquestDTO contaResquestDTO) {
        Conta conta = new Conta();
        conta.setDescricao(contaResquestDTO.getDescricao());
        conta.setValor(contaResquestDTO.getValor());
        conta.setDataVencimento(contaResquestDTO.getDataVencimento());
        conta.setCategoria(contaResquestDTO.getCategoria());
        conta.setTipoPagamento(contaResquestDTO.getTipoPagamento());
        conta.setRecorrente(contaResquestDTO.getRecorrente());

        return contaRepository.salvar(conta);
    }

    @Override
    public List<Conta> listar() {
        return List.of();
    }

    @Override
    public Conta buscarPorId(Long id) {
        return null;
    }

    @Override
    public Conta pagar(Long id) {
        return null;
    }

    @Override
    public void deletar(Long id) {

    }
}
