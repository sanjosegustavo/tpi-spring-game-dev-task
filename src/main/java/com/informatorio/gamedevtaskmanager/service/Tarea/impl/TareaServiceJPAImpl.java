package com.informatorio.gamedevtaskmanager.service.Tarea.impl;

import com.informatorio.gamedevtaskmanager.domain.Desarrollador;
import com.informatorio.gamedevtaskmanager.domain.Juego;
import com.informatorio.gamedevtaskmanager.domain.Tarea;
import com.informatorio.gamedevtaskmanager.enumeration.EstadoEnum;
import com.informatorio.gamedevtaskmanager.exception.NotFoundException;
import com.informatorio.gamedevtaskmanager.mapper.tarea.TareaDevMapper;
import com.informatorio.gamedevtaskmanager.mapper.tarea.TareaJuegoMapper;
import com.informatorio.gamedevtaskmanager.mapper.tarea.TareaMapper;
import com.informatorio.gamedevtaskmanager.mapper.tarea.TareaResponseMapper;
import com.informatorio.gamedevtaskmanager.model.dto.tarea.TareaDTO;
import com.informatorio.gamedevtaskmanager.model.dto.tarea.TareaDevDTO;
import com.informatorio.gamedevtaskmanager.model.dto.tarea.TareaJuegoDTO;
import com.informatorio.gamedevtaskmanager.model.dto.tarea.TareaResponseDTO;
import com.informatorio.gamedevtaskmanager.repository.desarrollador.DesarrolladorRepository;
import com.informatorio.gamedevtaskmanager.repository.juego.JuegoRepository;
import com.informatorio.gamedevtaskmanager.repository.tarea.TareaRepository;
import com.informatorio.gamedevtaskmanager.service.Tarea.TareaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@AllArgsConstructor
public class TareaServiceJPAImpl implements TareaService {

    private final TareaRepository tareaRepository;
    private final TareaMapper tareaMapper;
    private final JuegoRepository juegoRepository;
    private final DesarrolladorRepository desarrolladorRepository;
    private final TareaDevMapper tareaDevMapper;
    private final TareaResponseMapper tareaResponseMapper;
    private final TareaJuegoMapper tareaJuegoMapper;

    @Override
    public Optional<Tarea> createTarea(TareaDTO tareaDTO) {

        Optional<Juego> juegoOptional = juegoRepository.findById(UUID.fromString(tareaDTO.getIdJuego()));
        Optional<Desarrollador> desarrolladorOptional = desarrolladorRepository.findById(UUID.fromString(tareaDTO.getIdDesarrollador()));

        if(juegoOptional.isEmpty() || desarrolladorOptional.isEmpty()){
            return Optional.empty();
        }

        Tarea tarea = tareaMapper.tareaDTOtoTarea(tareaDTO);
        return Optional.of(tareaRepository.save(tarea));
    }

    @Override
    public Optional<TareaDevDTO> getTareaDev(UUID idDesarrollador) {
        Optional<Desarrollador> desarrolladorOptional = desarrolladorRepository.findById(idDesarrollador);

        if (desarrolladorOptional.isEmpty()){
            return Optional.empty();
        }

        List<Tarea> tareas = tareaRepository.findByDesarrollador(idDesarrollador);
        return Optional.of(tareaDevMapper.tareasToTareaDevDTO(tareas, desarrolladorOptional.get()));
    }

    @Override
    public Optional<Tarea> updateTarea(UUID idTarea, EstadoEnum estado) throws NotFoundException {

        Optional<Tarea> tareaOptional = tareaRepository.findById(idTarea);

        if (tareaOptional.isEmpty()){
            throw new NotFoundException();
        }

        updatingTarea(tareaOptional.get(), estado);
        return Optional.of(tareaRepository.save(tareaOptional.get()));
    }

    @Override
    public List<TareaResponseDTO> getTareas(EstadoEnum estado) {
        List<Tarea> tareas = tareaRepository.findByEstado(estado);
        return getListTareaResponseDTO(tareas);
    }

    @Override
    public List<TareaResponseDTO> getTareas(LocalDate fechaLimite) {
        List<Tarea> tareas = tareaRepository.findByFechaLimite(fechaLimite);
        return getListTareaResponseDTO(tareas);
    }

    @Override
    public List<TareaResponseDTO> getTareasAtrasadas(LocalDate fechaActual) {
        List<Tarea> tareas = tareaRepository.findByAtrasadas(fechaActual);
        return getListTareaResponseDTO(tareas);
    }

    @Override
    public Optional<TareaJuegoDTO> getTareaJuego(UUID idJuego) {
        Optional<Juego> juegoOptional = juegoRepository.findById(idJuego);

        if (juegoOptional.isEmpty()){
            return Optional.empty();
        }

        List<Tarea> tareas = tareaRepository.findByJuego(idJuego);
        return Optional.of(tareaJuegoMapper.tareasToTareaJuegoDTO(tareas, juegoOptional.get()));
    }


    private List<TareaResponseDTO> getListTareaResponseDTO(List<Tarea> tareas){
        List<TareaResponseDTO> tareaResponseDTOS = new ArrayList<>();
        for (Tarea tarea : tareas) {
            tareaResponseDTOS.add(tareaResponseMapper.tareaToTareaResponseDTO(tarea));
        }
        return tareaResponseDTOS;
    }


    private void updatingTarea(Tarea tareaUpdated, EstadoEnum estado) {
        tareaUpdated.setEstado(estado);
    }

}
