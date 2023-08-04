package com.informatorio.gamedevtaskmanager.service.desarrollador;

import com.informatorio.gamedevtaskmanager.domain.Desarrollador;
import com.informatorio.gamedevtaskmanager.model.dto.desarrollador.DesarrolladorDTO;

import java.util.Optional;

public interface DesarrolladorService {
    Optional<Desarrollador> createDesarrollador(DesarrolladorDTO desarrolladorDTO);
}
