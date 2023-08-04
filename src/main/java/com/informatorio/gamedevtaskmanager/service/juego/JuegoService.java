package com.informatorio.gamedevtaskmanager.service.juego;

import com.informatorio.gamedevtaskmanager.domain.Juego;
import com.informatorio.gamedevtaskmanager.exception.NotFoundException;
import com.informatorio.gamedevtaskmanager.model.dto.juego.JuegoDTO;
import com.informatorio.gamedevtaskmanager.model.dto.juego.JuegoDevsDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface JuegoService {
    Optional<Juego> createJuego(JuegoDTO juegoDTO);

    Optional<Juego> updateJuego(UUID idJuego, UUID idDesarrollador) throws NotFoundException;


    List<JuegoDTO> getJuegos(Boolean finalizados, Boolean noFinalizados);

    Optional<JuegoDevsDTO> getJuegosDevsById(UUID idJuego);

}

