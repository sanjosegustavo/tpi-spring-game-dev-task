package com.informatorio.gamedevtaskmanager.mapper.juego;

import com.informatorio.gamedevtaskmanager.domain.Juego;
import com.informatorio.gamedevtaskmanager.model.dto.juego.JuegoDTO;

public interface JuegoMapper {

    public Juego juegoDTOToJuego(JuegoDTO juegoDTO);

    public JuegoDTO juegoToJuegoDTO(Juego juego);
}
