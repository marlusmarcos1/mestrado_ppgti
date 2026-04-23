package ufrn.imd.eventos.domain.entidades.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class EventoResponseDTO {

    private Long id;
    private String nome;
    private String local;
    private LocalDateTime dataEvento;
    private Integer capacidade;

    private OrganizadorResponseDTO organizador;

    private List<ParticipanteResponseDTO> participantes;
}
