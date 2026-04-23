package ufrn.imd.eventos.domain.entidades.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ParticipanteRequestDTO {

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;
}
