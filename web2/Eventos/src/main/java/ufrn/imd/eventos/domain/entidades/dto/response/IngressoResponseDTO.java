package ufrn.imd.eventos.domain.entidades.dto.response;

import lombok.Data;
import ufrn.imd.eventos.domain.enums.TipoIngresso;

import java.math.BigDecimal;

@Data
public class IngressoResponseDTO {

    private Long id;
    private BigDecimal valor;
    private TipoIngresso tipo;
}
