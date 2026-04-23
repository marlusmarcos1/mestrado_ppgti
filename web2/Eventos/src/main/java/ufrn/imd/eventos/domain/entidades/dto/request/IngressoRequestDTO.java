package ufrn.imd.eventos.domain.entidades.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ufrn.imd.eventos.domain.enums.TipoIngresso;

import java.math.BigDecimal;

@Data
public class IngressoRequestDTO {

    @NotNull
    private BigDecimal valor;

    @NotNull
    private TipoIngresso tipo;

    @NotNull
    private Long eventoId;
}
