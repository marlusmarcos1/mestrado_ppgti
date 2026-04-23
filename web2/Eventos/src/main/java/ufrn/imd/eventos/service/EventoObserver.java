package ufrn.imd.eventos.service;

import ufrn.imd.eventos.domain.entidades.Evento;

public interface EventoObserver {
    void onEventoCriado(Evento evento);
}