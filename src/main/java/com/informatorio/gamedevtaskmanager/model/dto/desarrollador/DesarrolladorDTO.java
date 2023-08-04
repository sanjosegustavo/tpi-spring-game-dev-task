package com.informatorio.gamedevtaskmanager.model.dto.desarrollador;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DesarrolladorDTO {
    private String nombre;
    private String email;
    private String rol;
}
