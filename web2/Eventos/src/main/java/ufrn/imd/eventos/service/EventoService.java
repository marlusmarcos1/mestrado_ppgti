package ufrn.imd.eventos.service;

import org.springframework.stereotype.Service;
import ufrn.imd.eventos.domain.entidades.Evento;
import ufrn.imd.eventos.domain.entidades.Organizador;
import ufrn.imd.eventos.domain.entidades.dto.request.EventoRequestDTO;
import ufrn.imd.eventos.domain.entidades.dto.response.EventoResponseDTO;
import ufrn.imd.eventos.mapper.EventoMapper;
import ufrn.imd.eventos.repository.EventoRepository;
import ufrn.imd.eventos.repository.OrganizadorRepository;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;
    private final OrganizadorRepository organizadorRepository;
    private final EventoPublisher publisher;

    public EventoService(
            EventoRepository eventoRepository,
            OrganizadorRepository organizadorRepository,
            EventoPublisher publisher
    ) {
        this.eventoRepository = eventoRepository;
        this.organizadorRepository = organizadorRepository;
        this.publisher = publisher;
    }

    public EventoResponseDTO criar(EventoRequestDTO dto) {

        Organizador organizador = organizadorRepository.findById(dto.getOrganizadorId())
                .orElseThrow(() -> new RuntimeException("Organizador não encontrado"));

        Evento evento = EventoMapper.toEntity(dto, organizador);

        Evento salvo = eventoRepository.save(evento);

        publisher.notificarEventoCriado(salvo);

        return EventoMapper.toDTO(salvo);
    }
}
