package br.ufrn.imd.gestaofinanceiro.modelo.dtos;

import br.ufrn.imd.gestaofinanceiro.modelo.enums.Categoria;
import br.ufrn.imd.gestaofinanceiro.modelo.enums.StatusConta;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContaResponseDTO {
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private StatusConta status;
    private Categoria categoria;
}
