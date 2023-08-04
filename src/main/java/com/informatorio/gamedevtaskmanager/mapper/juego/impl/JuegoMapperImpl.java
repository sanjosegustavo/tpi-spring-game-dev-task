package com.informatorio.gamedevtaskmanager.mapper.juego.impl;

import com.informatorio.gamedevtaskmanager.domain.Juego;
import com.informatorio.gamedevtaskmanager.mapper.juego.JuegoMapper;
import com.informatorio.gamedevtaskmanager.model.dto.juego.JuegoDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class JuegoMapperImpl implements JuegoMapper {

    @Override
    public Juego juegoDTOToJuego(JuegoDTO juegoDTO) {
        return Juego.builder()
                .id(UUID.randomUUID())
                .titulo(juegoDTO.getTitulo())
                .descripcion(juegoDTO.getDescripcion())
                .fechaLanzamiento(LocalDate.parse(juegoDTO.getFechaLanzamiento()))
                .build();
    }

    @Override
    public JuegoDTO juegoToJuegoDTO(Juego juego) {
        return JuegoDTO.builder()
                .titulo(juego.getTitulo())
                .descripcion(juego.getDescripcion())
                .fechaLanzamiento(juego.getFechaLanzamiento().toString())
                .build();
    }
}
