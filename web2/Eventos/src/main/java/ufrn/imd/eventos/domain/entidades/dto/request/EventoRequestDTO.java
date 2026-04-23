package ufrn.imd.eventos.domain.entidades.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventoRequestDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String local;

    @NotNull
    private LocalDateTime dataEvento;

    @Min(1)
    private Integer capacidade;

    @NotNull
    private Long organizadorId;
}
