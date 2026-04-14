package br.ufrn.imd.gestaofinanceiro.modelo.entidades;

import br.ufrn.imd.gestaofinanceiro.modelo.enums.TipoPagamento;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pagamento {

    private Long id;
    private Long contaId;
    private BigDecimal valorPago;
    private LocalDate dataPagamento;
    private TipoPagamento tipoPagamento;
}
