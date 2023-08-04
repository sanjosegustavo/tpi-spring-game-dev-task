package com.informatorio.gamedevtaskmanager.mapper.tarea;

import com.informatorio.gamedevtaskmanager.domain.Desarrollador;
import com.informatorio.gamedevtaskmanager.domain.Tarea;
import com.informatorio.gamedevtaskmanager.model.dto.tarea.TareaDevDTO;

import java.util.List;

public interface TareaDevMapper {
    TareaDevDTO tareasToTareaDevDTO(List<Tarea> tareas, Desarrollador desarrollador);
}
