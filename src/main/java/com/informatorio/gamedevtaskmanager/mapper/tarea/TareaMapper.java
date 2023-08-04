package com.informatorio.gamedevtaskmanager.mapper.tarea;

import com.informatorio.gamedevtaskmanager.domain.Tarea;
import com.informatorio.gamedevtaskmanager.model.dto.tarea.TareaDTO;

public interface TareaMapper {

    Tarea tareaDTOtoTarea(TareaDTO tareaDTO);
}
