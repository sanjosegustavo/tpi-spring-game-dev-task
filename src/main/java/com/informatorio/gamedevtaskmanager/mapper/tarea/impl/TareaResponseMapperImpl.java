package com.informatorio.gamedevtaskmanager.mapper.tarea.impl;

import com.informatorio.gamedevtaskmanager.domain.Tarea;
import com.informatorio.gamedevtaskmanager.mapper.tarea.TareaResponseMapper;
import com.informatorio.gamedevtaskmanager.model.dto.tarea.TareaResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TareaResponseMapperImpl implements TareaResponseMapper {
    @Override
    public TareaResponseDTO tareaToTareaResponseDTO(Tarea tarea) {
        return TareaResponseDTO.builder()
                .descripcion(tarea.getDescripcion())
                .estado(tarea.getEstado().toString())
                .fechaLimite(tarea.getFechaLimite().toString())
                .build();
    }
}
