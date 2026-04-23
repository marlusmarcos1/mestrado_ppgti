package ufrn.imd.eventos.domain.entidades;

import jakarta.persistence.*;
import lombok.*;
import ufrn.imd.eventos.domain.enums.TipoIngresso;

import java.math.BigDecimal;

@Entity
@Table(name = "ingressos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ingresso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private TipoIngresso tipo;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;
}