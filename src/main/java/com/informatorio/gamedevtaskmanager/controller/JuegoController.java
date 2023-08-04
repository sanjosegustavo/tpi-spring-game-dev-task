package com.informatorio.gamedevtaskmanager.controller;

import com.informatorio.gamedevtaskmanager.domain.Juego;
import com.informatorio.gamedevtaskmanager.exception.NotFoundException;
import com.informatorio.gamedevtaskmanager.model.dto.juego.JuegoDTO;
import com.informatorio.gamedevtaskmanager.model.dto.juego.JuegoDevsDTO;
import com.informatorio.gamedevtaskmanager.service.juego.JuegoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/juego")
@Slf4j
public class JuegoController {

    JuegoService juegoService;

    @Autowired
    public JuegoController(JuegoService juegoService) {
        this.juegoService = juegoService;
    }

    @PostMapping()
    public ResponseEntity createJuego(@RequestBody JuegoDTO juegoDTO){
        log.info("Creación de un nuevo juego");
        Optional<Juego> juegoOptional = juegoService.createJuego(juegoDTO);

        if (juegoOptional.isPresent()){
            log.info("Juego creado.");
            HttpHeaders headers = new HttpHeaders(); // cabecera de la respuesta
            headers.add("Location", "/api/v1/juego/" + juegoOptional.get().getId());
            return new ResponseEntity(headers, HttpStatus.CREATED);
        } else {
            log.info("El titulo del juego ya existe");
            return new ResponseEntity(HttpStatus.ALREADY_REPORTED);
        }

    }

    @PutMapping("/{idJuego}")
    public ResponseEntity updateJuego(@PathVariable(value = "idJuego") UUID idJuego,
                                      @RequestParam(required = false, name = "idDesarrollador") UUID idDesarrollador)
            throws NotFoundException {
        if (idDesarrollador != null){
            Optional<Juego> juegoOptional = juegoService.updateJuego(idJuego, idDesarrollador);

            if (juegoOptional.isPresent()){
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
    public List<JuegoDTO> getJuegos(@RequestParam(required = false, name = "finalizados") Boolean finalizados,
                                    @RequestParam(required = false, name = "noFinalizados") Boolean noFinalizados) {

        if (finalizados != null && noFinalizados != null){
            return juegoService.getJuegos(finalizados, noFinalizados);
        } else if (finalizados == null && noFinalizados != null) {
            return juegoService.getJuegos(false, noFinalizados);
        } else if (finalizados != null) {
            return juegoService.getJuegos(finalizados, false);
        }else {
            return juegoService.getJuegos(false, false);
        }

    }

    @GetMapping("/devs/{idJuego}")
    public JuegoDevsDTO getJuegosDevsById(@PathVariable(value = "idJuego") UUID idJuego) throws NotFoundException {
        Optional<JuegoDevsDTO> juegoDevsDTOOptional = juegoService.getJuegosDevsById(idJuego);

        if (juegoDevsDTOOptional.isPresent()){
            return juegoDevsDTOOptional.get();
        }
        throw new NotFoundException();
    }
}
