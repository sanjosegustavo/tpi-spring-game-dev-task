package com.informatorio.gamedevtaskmanager.mapper.tarea.impl;

import com.informatorio.gamedevtaskmanager.domain.Desarrollador;
import com.informatorio.gamedevtaskmanager.domain.Juego;
import com.informatorio.gamedevtaskmanager.domain.Tarea;
import com.informatorio.gamedevtaskmanager.enumeration.EstadoEnum;
import com.informatorio.gamedevtaskmanager.mapper.tarea.TareaMapper;
import com.informatorio.gamedevtaskmanager.model.dto.tarea.TareaDTO;
import com.informatorio.gamedevtaskmanager.repository.desarrollador.DesarrolladorRepository;
import com.informatorio.gamedevtaskmanager.repository.juego.JuegoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;


@Component
@AllArgsConstructor
public class TareaMapperImpl implements TareaMapper {

    private final JuegoRepository juegoRepository;
    private final DesarrolladorRepository desarrolladorRepository;

    @Override
    public Tarea tareaDTOtoTarea(TareaDTO tareaDTO) {
        Tarea tarea = Tarea.builder()
                .id(UUID.randomUUID())
                .descripcion(tareaDTO.getDescripcion())
                .fechaLimite(LocalDate.parse(tareaDTO.getFechaLimite()))
                .estado(EstadoEnum.valueOf("PENDIENTE"))
                .build();

        Optional<Juego> juegoOptional = juegoRepository.findById(UUID.fromString(tareaDTO.getIdJuego()));
        Optional<Desarrollador> desarrolladorOptional = desarrolladorRepository.findById(UUID.fromString(tareaDTO.getIdDesarrollador()));

        if (juegoOptional.isPresent()) {
            tarea.setJuego(juegoOptional.get());
        }
        if (desarrolladorOptional.isPresent()) {
            tarea.setDesarrolladorResponsable(desarrolladorOptional.get());
        }

        return tarea;
    }
}
