package com.informatorio.gamedevtaskmanager.model.dto.juego;


import com.informatorio.gamedevtaskmanager.model.dto.desarrollador.DesarrolladorDTO;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JuegoDevsDTO {
    private String titulo;
    private String descripcion;
    private List<DesarrolladorDTO> desarrolladorDTOS;
}
