package com.informatorio.gamedevtaskmanager.mapper.desarrollador;

import com.informatorio.gamedevtaskmanager.domain.Desarrollador;
import com.informatorio.gamedevtaskmanager.model.dto.desarrollador.DesarrolladorDTO;

public interface DesarrolladorMapper {
    Desarrollador desarrolladorDTOtoDesarrollador(DesarrolladorDTO desarrolladorDTO);

    DesarrolladorDTO desarrolladorToDesarrolladorDTO(Desarrollador desarrollador);
}
