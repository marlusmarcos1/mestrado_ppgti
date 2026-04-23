package ufrn.imd.eventos.service;

import org.springframework.stereotype.Service;
import ufrn.imd.eventos.domain.entidades.Evento;
import ufrn.imd.eventos.domain.entidades.Ingresso;
import ufrn.imd.eventos.domain.entidades.dto.request.IngressoRequestDTO;
import ufrn.imd.eventos.domain.entidades.dto.response.IngressoResponseDTO;

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

        return toDTO(repository.save(ingresso));
    }
}
