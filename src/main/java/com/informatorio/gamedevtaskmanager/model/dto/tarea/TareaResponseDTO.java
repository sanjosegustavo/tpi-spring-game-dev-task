package com.informatorio.gamedevtaskmanager.model.dto.tarea;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TareaResponseDTO {
    private String descripcion;
    private String estado;
    private String fechaLimite;
}
