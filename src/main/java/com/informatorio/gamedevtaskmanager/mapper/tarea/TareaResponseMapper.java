package com.informatorio.gamedevtaskmanager.mapper.tarea;

import com.informatorio.gamedevtaskmanager.domain.Tarea;
import com.informatorio.gamedevtaskmanager.model.dto.tarea.TareaResponseDTO;

public interface TareaResponseMapper {
    TareaResponseDTO tareaToTareaResponseDTO(Tarea tarea);
}
