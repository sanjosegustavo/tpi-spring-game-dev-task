package com.informatorio.gamedevtaskmanager.controller;

import com.informatorio.gamedevtaskmanager.domain.Tarea;
import com.informatorio.gamedevtaskmanager.enumeration.EstadoEnum;
import com.informatorio.gamedevtaskmanager.exception.NotFoundException;
import com.informatorio.gamedevtaskmanager.model.dto.tarea.TareaDTO;
import com.informatorio.gamedevtaskmanager.model.dto.tarea.TareaDevDTO;
import com.informatorio.gamedevtaskmanager.model.dto.tarea.TareaJuegoDTO;
import com.informatorio.gamedevtaskmanager.model.dto.tarea.TareaResponseDTO;
import com.informatorio.gamedevtaskmanager.service.Tarea.TareaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tarea")
@Slf4j
public class TareaController {

    TareaService tareaService;

    @Autowired
    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @PostMapping()
    public ResponseEntity createTarea(@RequestBody TareaDTO tareaDTO){
        log.info("Creación de nueva tarea.");
        Optional<Tarea> tareaOptional = tareaService.createTarea(tareaDTO);

        HttpHeaders headers = new HttpHeaders();
        if (tareaOptional.isPresent()){
            log.info("Tarea creada.");
            headers.add("Location", "/api/v1/tarea/" + tareaOptional.get().getId());
            return new ResponseEntity(headers, HttpStatus.CREATED);
        } else {
            log.info("No se pudo crear la tarea.");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/devs/{idDesarrollador}")
    public TareaDevDTO getTareaDev(@PathVariable(value = "idDesarrollador") UUID idDesarrollador) throws NotFoundException {
        Optional<TareaDevDTO> tareaDevDTOOptional = tareaService.getTareaDev(idDesarrollador);

        if (tareaDevDTOOptional.isPresent()){
            return tareaDevDTOOptional.get();
        }
        throw new NotFoundException();
    }

    @PutMapping("/{idTarea}")
    ResponseEntity updateTarea(@PathVariable(value = "idTarea") UUID idTarea,
                               @RequestParam(required = false, name = "estado") EstadoEnum estado)
        throws NotFoundException {

        if (estado != null){
            Optional<Tarea> tareaOptional = tareaService.updateTarea(idTarea, estado);
            if (tareaOptional.isPresent()){
                log.info("Juego actualizado");
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            } else {
                log.info("Error de la aplicación al intentar actualizar el juego.");
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping()
    public List<TareaResponseDTO> getTareas(@RequestParam(required = false, name = "estado") EstadoEnum estado,
                                 @RequestParam(required = false, name = "fechaLimite") LocalDate fechaLimite)
            throws NotFoundException {
        List<TareaResponseDTO> tareaResponseDTOS;

        if (estado != null) {
            tareaResponseDTOS = tareaService.getTareas(estado);
        } else {
            tareaResponseDTOS = tareaService.getTareas(fechaLimite);
        }
        return tareaResponseDTOS;
    }

    @GetMapping("/atrasadas")
    public List<TareaResponseDTO> getTareasAtrasadas()  {
        List<TareaResponseDTO> tareaResponseDTOS = tareaService.getTareasAtrasadas(LocalDate.now());
        return tareaResponseDTOS;
    }

    @GetMapping("/juego/{idJuego}")
    public TareaJuegoDTO getTareaJuego(@PathVariable(value = "idJuego") UUID idJuego) throws NotFoundException {
        Optional<TareaJuegoDTO> tareaJuegoDTOOptional = tareaService.getTareaJuego(idJuego);

        if (tareaJuegoDTOOptional.isPresent()){
            return tareaJuegoDTOOptional.get();
        }
        throw new NotFoundException();
    }


}
