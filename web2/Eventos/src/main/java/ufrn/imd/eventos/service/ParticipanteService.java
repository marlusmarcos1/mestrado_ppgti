package ufrn.imd.eventos.service;

import org.springframework.stereotype.Service;
import ufrn.imd.eventos.domain.entidades.Evento;
import ufrn.imd.eventos.domain.entidades.Participante;
import ufrn.imd.eventos.domain.entidades.dto.EventoParticipanteDTO;

@Service
public class ParticipanteService {

    private final EventoRepository eventoRepository;
    private final ParticipanteRepository participanteRepository;

    public ParticipanteService(
            EventoRepository eventoRepository,
            ParticipanteRepository participanteRepository
    ) {
        this.eventoRepository = eventoRepository;
        this.participanteRepository = participanteRepository;
    }

    public void adicionarParticipante(EventoParticipanteDTO dto) {

        Evento evento = eventoRepository.findById(dto.getEventoId())
                .orElseThrow();

        Participante participante = participanteRepository.findById(dto.getParticipanteId())
                .orElseThrow();

        evento.getParticipantes().add(participante);

        eventoRepository.save(evento);
    }
}
