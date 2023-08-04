package com.informatorio.gamedevtaskmanager.model.dto.tarea;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TareaJuegoDTO {
    private String titulo;
    private String fechaLanzamiento;
    private List<TareaResponseDTO> tareaResponseDTOS;
}
