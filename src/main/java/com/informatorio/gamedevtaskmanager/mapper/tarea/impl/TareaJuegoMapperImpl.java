package com.informatorio.gamedevtaskmanager.mapper.tarea.impl;

import com.informatorio.gamedevtaskmanager.domain.Juego;
import com.informatorio.gamedevtaskmanager.domain.Tarea;
import com.informatorio.gamedevtaskmanager.mapper.tarea.TareaJuegoMapper;
import com.informatorio.gamedevtaskmanager.mapper.tarea.TareaResponseMapper;
import com.informatorio.gamedevtaskmanager.model.dto.tarea.TareaJuegoDTO;
import com.informatorio.gamedevtaskmanager.model.dto.tarea.TareaResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class TareaJuegoMapperImpl implements TareaJuegoMapper {

    private final TareaResponseMapper tareaResponseMapper;

    @Override
    public TareaJuegoDTO tareasToTareaJuegoDTO(List<Tarea> tareas, Juego juego) {
        List<TareaResponseDTO> tareaResponseDTOS = new ArrayList<>();

        for (Tarea tarea : tareas) {
            tareaResponseDTOS.add(tareaResponseMapper.tareaToTareaResponseDTO(tarea));
        }

        return TareaJuegoDTO.builder()
                .titulo(juego.getTitulo())
                .fechaLanzamiento(juego.getFechaLanzamiento().toString())
                .tareaResponseDTOS(tareaResponseDTOS)
                .build();
    }
}
