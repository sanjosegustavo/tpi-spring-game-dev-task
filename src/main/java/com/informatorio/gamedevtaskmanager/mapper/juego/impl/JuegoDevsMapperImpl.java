package com.informatorio.gamedevtaskmanager.mapper.juego.impl;

import com.informatorio.gamedevtaskmanager.domain.Desarrollador;
import com.informatorio.gamedevtaskmanager.domain.Juego;
import com.informatorio.gamedevtaskmanager.mapper.desarrollador.DesarrolladorMapper;
import com.informatorio.gamedevtaskmanager.mapper.juego.JuegoDevsMapper;
import com.informatorio.gamedevtaskmanager.model.dto.desarrollador.DesarrolladorDTO;
import com.informatorio.gamedevtaskmanager.model.dto.juego.JuegoDevsDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class JuegoDevsMapperImpl implements JuegoDevsMapper {

    private final DesarrolladorMapper desarrolladorMapper;

    @Override
    public JuegoDevsDTO juegoToJuegoDevsDTO(Juego juego) {
        JuegoDevsDTO juegoDevsDTO = JuegoDevsDTO.builder()
                .titulo(juego.getTitulo())
                .descripcion(juego.getDescripcion())
                .build();

        List<DesarrolladorDTO> desarrolladorDTOS = new ArrayList<>();
        for (Desarrollador desarrollador : juego.getDesarrolladores()) {
            desarrolladorDTOS.add(desarrolladorMapper.desarrolladorToDesarrolladorDTO(desarrollador));
        }

        juegoDevsDTO.setDesarrolladorDTOS(desarrolladorDTOS);
        return juegoDevsDTO;
    }

}
