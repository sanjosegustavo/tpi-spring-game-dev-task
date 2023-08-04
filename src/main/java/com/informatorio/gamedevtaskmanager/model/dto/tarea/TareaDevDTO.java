package com.informatorio.gamedevtaskmanager.model.dto.tarea;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TareaDevDTO {
    private String nombre;
    private List<TareaResponseDTO> tareaResponseDTOS;
}
