package br.ufrn.imd.gestaofinanceiro.modelo.entidades;

import br.ufrn.imd.gestaofinanceiro.modelo.enums.Categoria;
import br.ufrn.imd.gestaofinanceiro.modelo.enums.StatusConta;
import br.ufrn.imd.gestaofinanceiro.modelo.enums.TipoPagamento;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Conta {
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private StatusConta status;
    private Categoria categoria;
    private TipoPagamento tipoPagamento;
    private Boolean recorrente;

}
