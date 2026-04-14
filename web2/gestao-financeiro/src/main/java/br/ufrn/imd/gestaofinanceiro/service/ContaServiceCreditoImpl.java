package br.ufrn.imd.gestaofinanceiro.service;

import br.ufrn.imd.gestaofinanceiro.exception.RegraNegocioException;
import br.ufrn.imd.gestaofinanceiro.modelo.dtos.ContaResquestDTO;
import br.ufrn.imd.gestaofinanceiro.modelo.entidades.Conta;
import br.ufrn.imd.gestaofinanceiro.modelo.enums.StatusConta;
import br.ufrn.imd.gestaofinanceiro.modelo.enums.TipoPagamento;
import br.ufrn.imd.gestaofinanceiro.repository.ContaRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Builder
@Service
public class ContaServiceCreditoImpl implements  ContaService {

    private final ContaRepository contaRepository;

    @Override
    public Conta criar(ContaResquestDTO contaResquestDTO) {
        Conta conta = new Conta();
        conta.setDescricao(contaResquestDTO.getDescricao());
        conta.setValor(contaResquestDTO.getValor()); // sem desconto
        conta.setDataVencimento(contaResquestDTO.getDataVencimento());
        conta.setCategoria(contaResquestDTO.getCategoria());
        conta.setStatus(StatusConta.PENDENTE);

        return contaRepository.salvar(conta);
    }

    @Override
    public List<Conta> listar() {
        return contaRepository.findAll();
    }

    @Override
    public Conta buscarPorId(Long id) {
        return contaRepository.findbyID(id)
                .orElseThrow(() -> new RegraNegocioException("Conta não encontrada com ID: " + id));
    }

    @Override
    public Conta pagar(Long id) {
        Conta conta = buscarPorId(id);
        if (conta.getStatus() == StatusConta.PAGA) {
            throw new RegraNegocioException("Conta paga");
        }
        LocalDate agora = LocalDate.now();
        BigDecimal valor = conta.getValor().multiply(new BigDecimal("1.05"));
        if (agora.isAfter(conta.getDataVencimento())) {
            valor = valor.multiply(new BigDecimal("1.10"));
        }
        conta.setValor(valor);
        conta.setTipoPagamento(TipoPagamento.CREDITO);
        conta.setDataPagamento(agora);
        conta.setStatus(StatusConta.PAGA);
        return  conta;
    }


    @Override
    public void deletar(Long id) {
        Conta conta = buscarPorId(id);
        if (conta.getStatus() == StatusConta.PAGA) {
            throw new RegraNegocioException("Não pode deletar conta paga");
        }
        contaRepository.delete(id);
    }
}
