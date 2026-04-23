package ufrn.imd.eventos.service;

import org.springframework.stereotype.Service;
import ufrn.imd.eventos.domain.entidades.Evento;
import ufrn.imd.eventos.domain.entidades.Ingresso;
import ufrn.imd.eventos.domain.entidades.dto.request.IngressoRequestDTO;
import ufrn.imd.eventos.domain.entidades.dto.response.IngressoResponseDTO;
import ufrn.imd.eventos.repository.EventoRepository;
import ufrn.imd.eventos.repository.IngressoRepository;

import static ufrn.imd.eventos.mapper.EventoMapper.toDTO;

@Service
public class IngressoService {

    private final IngressoRepository repository;
    private final EventoRepository eventoRepository;

    public IngressoService(
            IngressoRepository repository,
            EventoRepository eventoRepository
    ) {
        this.repository = repository;
        this.eventoRepository = eventoRepository;
    }

    public IngressoResponseDTO criar(IngressoRequestDTO dto) {

        Evento evento = eventoRepository.findById(dto.getEventoId())
                .orElseThrow();

        Ingresso ingresso = new Ingresso();
        ingresso.setValor(dto.getValor());
        ingresso.setTipo(dto.getTipo());
        ingresso.setEvento(evento);

        Ingresso res = repository.save(ingresso);
        return toDTO(ingresso);

    }
    private IngressoResponseDTO toDTO(Ingresso ingresso) {
        IngressoResponseDTO dto = new IngressoResponseDTO();
        dto.setId(ingresso.getId());
        dto.setValor(ingresso.getValor());
        dto.setTipo(ingresso.getTipo());
        return dto;
    }
}
