package ufrn.imd.eventos.domain.entidades.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EventoParticipanteDTO {

    @NotNull
    private Long eventoId;

    @NotNull
    private Long participanteId;
}
