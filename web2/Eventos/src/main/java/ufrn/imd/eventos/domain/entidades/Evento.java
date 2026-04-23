package ufrn.imd.eventos.domain.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "eventos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String local;

    private LocalDateTime dataEvento;

    private Integer capacidade;

    @ManyToOne
    @JoinColumn(name = "organizador_id")
    private Organizador organizador;

    @ManyToMany
    @JoinTable(
            name = "evento_participante",
            joinColumns = @JoinColumn(name = "evento_id"),
            inverseJoinColumns = @JoinColumn(name = "participante_id")
    )
    private List<Participante> participantes = new ArrayList<>();

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    private List<Ingresso> ingressos = new ArrayList<>();
}