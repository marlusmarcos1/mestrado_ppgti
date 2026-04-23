package ufrn.imd.eventos.domain.entidades.dto.response;

import ufrn.imd.eventos.domain.enums.TipoIngresso;

import java.math.BigDecimal;

public class IngressoResponseDTO {

    private Long id;
    private BigDecimal valor;
    private TipoIngresso tipo;
}
