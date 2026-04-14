package br.ufrn.imd.gestaofinanceiro.service;

import br.ufrn.imd.gestaofinanceiro.modelo.dtos.ContaResquestDTO;
import br.ufrn.imd.gestaofinanceiro.modelo.entidades.Conta;

import java.util.List;

public interface ContaService
{
    Conta criar (ContaResquestDTO contaResquestDTO);
    List<Conta> listar();
    Conta buscarPorId(Long id);
    Conta pagar(Long id);
    void deletar(Long id);

}
