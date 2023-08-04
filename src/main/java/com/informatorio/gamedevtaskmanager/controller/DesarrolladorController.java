package com.informatorio.gamedevtaskmanager.controller;

import com.informatorio.gamedevtaskmanager.domain.Desarrollador;
import com.informatorio.gamedevtaskmanager.exception.NotFoundException;
import com.informatorio.gamedevtaskmanager.model.dto.desarrollador.DesarrolladorDTO;
import com.informatorio.gamedevtaskmanager.service.desarrollador.DesarrolladorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/desarrollador")
@Slf4j
public class DesarrolladorController {

    DesarrolladorService desarrolladorService;

    @Autowired
    public DesarrolladorController(DesarrolladorService desarrolladorService) {
        this.desarrolladorService = desarrolladorService;
    }

    @PostMapping
    public ResponseEntity createDesarrollador(@RequestBody DesarrolladorDTO desarrolladorDTO){
        log.info("Creación de un nuevo desarrollador");
        Optional<Desarrollador> desarrolladorOptional = desarrolladorService.createDesarrollador(desarrolladorDTO);

        HttpHeaders headers = new HttpHeaders();
        if (desarrolladorOptional.isPresent()){
            log.info("Crea el desarrollador.");
            headers.add("Location", "/api/v1/desarrollador/" + desarrolladorOptional.get().getId());
            return new ResponseEntity(headers, HttpStatus.CREATED);
        } else {
            log.info("El desarrollador ya existe.");
            headers.add("ErrorInfo", "El desarrollador ya existe.");
            return new ResponseEntity(headers,HttpStatus.BAD_REQUEST);
        }
    }

}
