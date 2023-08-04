package com.informatorio.gamedevtaskmanager.model.dto.tarea;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TareaDTO {
    private String descripcion;
    private String fechaLimite;
    private String idJuego;
    private String idDesarrollador;
}
