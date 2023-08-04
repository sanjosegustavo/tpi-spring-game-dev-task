package com.informatorio.gamedevtaskmanager.mapper.tarea;

import com.informatorio.gamedevtaskmanager.domain.Juego;
import com.informatorio.gamedevtaskmanager.domain.Tarea;
import com.informatorio.gamedevtaskmanager.model.dto.tarea.TareaJuegoDTO;

import java.util.List;

public interface TareaJuegoMapper {
    TareaJuegoDTO tareasToTareaJuegoDTO(List<Tarea> tareas, Juego juego);
}
