package ufrn.imd.eventos.domain.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "participantes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Participante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    @ManyToMany(mappedBy = "participantes")
    private List<Evento> eventos = new ArrayList<>();
}

