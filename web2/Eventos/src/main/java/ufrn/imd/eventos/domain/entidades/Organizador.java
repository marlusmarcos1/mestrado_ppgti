package ufrn.imd.eventos.domain.entidades;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "organizadores")
public class Organizador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    @OneToMany(mappedBy = "organizador")
    private List<Evento> eventos = new ArrayList<>();
}