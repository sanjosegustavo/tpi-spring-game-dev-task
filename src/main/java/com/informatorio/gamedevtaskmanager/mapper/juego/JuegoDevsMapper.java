package com.informatorio.gamedevtaskmanager.mapper.juego;

import com.informatorio.gamedevtaskmanager.domain.Juego;
import com.informatorio.gamedevtaskmanager.model.dto.juego.JuegoDevsDTO;

public interface JuegoDevsMapper {

    JuegoDevsDTO juegoToJuegoDevsDTO(Juego juego);
}
