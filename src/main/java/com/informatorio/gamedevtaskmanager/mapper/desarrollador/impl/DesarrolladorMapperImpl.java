package com.informatorio.gamedevtaskmanager.mapper.desarrollador.impl;

import com.informatorio.gamedevtaskmanager.domain.Desarrollador;
import com.informatorio.gamedevtaskmanager.enumeration.RolEnum;
import com.informatorio.gamedevtaskmanager.mapper.desarrollador.DesarrolladorMapper;
import com.informatorio.gamedevtaskmanager.model.dto.desarrollador.DesarrolladorDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class DesarrolladorMapperImpl implements DesarrolladorMapper {
    @Override
    public Desarrollador desarrolladorDTOtoDesarrollador(DesarrolladorDTO desarrolladorDTO) {

        return Desarrollador.builder()
                .id(UUID.randomUUID())
                .nombre(desarrolladorDTO.getNombre())
                .email(desarrolladorDTO.getEmail())
                .rol(RolEnum.valueOf(desarrolladorDTO.getRol()))
                .build();
    }

    @Override
    public DesarrolladorDTO desarrolladorToDesarrolladorDTO(Desarrollador desarrollador) {
        return DesarrolladorDTO.builder()
                .nombre(desarrollador.getNombre())
                .email(desarrollador.getEmail())
                .rol(desarrollador.getRol().toString())
                .build();
    }
}
