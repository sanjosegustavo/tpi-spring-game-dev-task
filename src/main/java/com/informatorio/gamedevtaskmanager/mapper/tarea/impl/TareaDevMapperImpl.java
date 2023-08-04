package com.informatorio.gamedevtaskmanager.mapper.tarea.impl;

import com.informatorio.gamedevtaskmanager.domain.Desarrollador;
import com.informatorio.gamedevtaskmanager.domain.Tarea;
import com.informatorio.gamedevtaskmanager.mapper.tarea.TareaDevMapper;
import com.informatorio.gamedevtaskmanager.mapper.tarea.TareaResponseMapper;
import com.informatorio.gamedevtaskmanager.model.dto.tarea.TareaDevDTO;
import com.informatorio.gamedevtaskmanager.model.dto.tarea.TareaResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class TareaDevMapperImpl implements TareaDevMapper {

    private final TareaResponseMapper tareaResponseMapper;

    @Override
    public TareaDevDTO tareasToTareaDevDTO(List<Tarea> tareas, Desarrollador desarrollador) {
        List<TareaResponseDTO> tareaResponseDTOS = new ArrayList<>();

        for (Tarea tarea : tareas) {
            tareaResponseDTOS.add(tareaResponseMapper.tareaToTareaResponseDTO(tarea));
        }

        return TareaDevDTO.builder()
                .nombre(desarrollador.getNombre())
                .tareaResponseDTOS(tareaResponseDTOS)
                .build();
    }
}
