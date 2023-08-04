package com.informatorio.gamedevtaskmanager.model.dto.juego;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JuegoDTO {
    private String titulo;
    private String descripcion;
    private String fechaLanzamiento;
}
