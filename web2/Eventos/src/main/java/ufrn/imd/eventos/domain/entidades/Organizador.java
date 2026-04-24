package ufrn.imd.eventos.domain.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "organizadores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Organizador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "organizador")
    private List<Evento> eventos = new ArrayList<>();
}