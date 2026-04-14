package br.ufrn.imd.gestaofinanceiro.modelo.dtos;

import br.ufrn.imd.gestaofinanceiro.modelo.enums.Categoria;
import br.ufrn.imd.gestaofinanceiro.modelo.enums.TipoPagamento;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContaResquestDTO {

    @NotBlank
    private String descricao;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal valor;

    @NotNull
    private LocalDate dataVencimento;

    @NotNull
    private Categoria categoria;

    @NotNull
    private TipoPagamento tipoPagamento;

    private Boolean recorrente;

}
