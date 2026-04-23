package ufrn.imd.eventos.domain.entidades.dto;

import jakarta.validation.constraints.NotNull;

public class EventoParticipanteDTO {

    @NotNull
    private Long eventoId;

    @NotNull
    private Long participanteId;
}
