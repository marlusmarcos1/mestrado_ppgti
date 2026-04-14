package br.ufrn.imd.gestaofinanceiro.service;

import br.ufrn.imd.gestaofinanceiro.exception.RegraNegocioException;
import br.ufrn.imd.gestaofinanceiro.modelo.dtos.ContaResquestDTO;
import br.ufrn.imd.gestaofinanceiro.modelo.entidades.Conta;
import br.ufrn.imd.gestaofinanceiro.modelo.enums.StatusConta;
import br.ufrn.imd.gestaofinanceiro.modelo.enums.TipoPagamento;
import br.ufrn.imd.gestaofinanceiro.repository.ContaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service("pix")
public class ContaServicePixImpl implements ContaService{

    private final ContaRepository repository;

    public ContaServicePixImpl(ContaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Conta criar(ContaResquestDTO dto) {
        Conta conta = new Conta();
        conta.setDescricao(dto.getDescricao());
        conta.setValor(dto.getValor());
        conta.setDataVencimento(dto.getDataVencimento());
        conta.setCategoria(dto.getCategoria());
        conta.setStatus(StatusConta.PENDENTE);

        return repository.salvar(conta);
    }

    @Override
    public List<Conta> listar() {
        return repository.findAll();
    }

    @Override
    public Conta buscarPorId(Long id) {
        return repository.findbyID(id)
                .orElseThrow(() -> new RegraNegocioException("Conta não encontrada"));
    }

    @Override
    public Conta pagar(Long id) {
        Conta conta = buscarPorId(id);
        if (conta.getStatus() == StatusConta.PAGA) {
            throw new RegraNegocioException("Conta paga");
        }
        LocalDate agora = LocalDate.now();
        BigDecimal valor = conta.getValor();
        if (agora.isAfter(conta.getDataVencimento())) {
            valor = valor.multiply(new BigDecimal("1.10"));
        }
        conta.setValor(valor);
        conta.setDataPagamento(agora);
        conta.setStatus(StatusConta.PAGA);
        conta.setTipoPagamento(TipoPagamento.PIX);
        return  conta;
    }

    @Override
    public void deletar(Long id) {
        repository.delete(id);
    }
}
