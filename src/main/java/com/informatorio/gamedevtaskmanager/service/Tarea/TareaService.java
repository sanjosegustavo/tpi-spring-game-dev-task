package com.informatorio.gamedevtaskmanager.service.Tarea;

import com.informatorio.gamedevtaskmanager.domain.Tarea;
import com.informatorio.gamedevtaskmanager.enumeration.EstadoEnum;
import com.informatorio.gamedevtaskmanager.exception.NotFoundException;
import com.informatorio.gamedevtaskmanager.model.dto.tarea.TareaDTO;
import com.informatorio.gamedevtaskmanager.model.dto.tarea.TareaDevDTO;
import com.informatorio.gamedevtaskmanager.model.dto.tarea.TareaJuegoDTO;
import com.informatorio.gamedevtaskmanager.model.dto.tarea.TareaResponseDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TareaService {
    Optional<Tarea> createTarea(TareaDTO tareaDTO);

    Optional<TareaDevDTO> getTareaDev(UUID idDesarrollador);

    Optional<Tarea> updateTarea(UUID idTarea, EstadoEnum estado) throws NotFoundException;

    List<TareaResponseDTO> getTareas(EstadoEnum estado);

    List<TareaResponseDTO> getTareas(LocalDate fechaLimite);

    List<TareaResponseDTO> getTareasAtrasadas(LocalDate fechaActual);

    Optional<TareaJuegoDTO> getTareaJuego(UUID idJuego);

}


