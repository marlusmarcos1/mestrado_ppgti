package ufrn.imd.eventos.mapper;

import ufrn.imd.eventos.domain.entidades.Evento;
import ufrn.imd.eventos.domain.entidades.Organizador;
import ufrn.imd.eventos.domain.entidades.dto.request.EventoRequestDTO;
import ufrn.imd.eventos.domain.entidades.dto.response.EventoResponseDTO;

public class EventoMapper {

    public static Evento toEntity(EventoRequestDTO dto, Organizador org) {
        return Evento.builder()
                .nome(dto.getNome())
                .local(dto.getLocal())
                .dataEvento(dto.getDataEvento())
                .capacidade(dto.getCapacidade())
                .organizador(org)
                .build();
    }

    public static EventoResponseDTO toDTO(Evento evento) {
        EventoResponseDTO dto = new EventoResponseDTO();
        dto.setId(evento.getId());
        dto.setNome(evento.getNome());
        dto.setLocal(evento.getLocal());
        dto.setDataEvento(evento.getDataEvento());
        dto.setCapacidade(evento.getCapacidade());
        return dto;
    }
}
