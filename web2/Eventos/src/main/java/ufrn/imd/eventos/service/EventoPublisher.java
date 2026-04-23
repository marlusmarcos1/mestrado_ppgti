package ufrn.imd.eventos.service;

import org.springframework.stereotype.Service;
import ufrn.imd.eventos.domain.entidades.Evento;

import java.util.List;

@Service
public class EventoPublisher {

    private final List<EventoObserver> observers;

    public EventoPublisher(List<EventoObserver> observers) {
        this.observers = observers;
    }

    public void notificarEventoCriado(Evento evento) {
        observers.forEach(o -> o.onEventoCriado(evento));
    }
}
