package com.informatorio.gamedevtaskmanager.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Juego {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(updatable = false, nullable = false, length = 36, columnDefinition = "varchar(36)")
    private UUID id;

    @Column(length = 100, columnDefinition = "varchar(100)", updatable = true, nullable = false)
    private String titulo;

    @Column(length = 300, columnDefinition = "varchar(300)", updatable = true, nullable = false)
    private String descripcion;

    @Builder.Default
    @OneToMany
    private List<Desarrollador> desarrolladores = new ArrayList<>();

    @Column(updatable = true, nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate fechaLanzamiento;

    @Builder.Default
    @OneToMany(mappedBy = "juego")
    private List<Tarea> tareas = new ArrayList<>();


    public void addDesarrollador(Desarrollador desarrollador){
        if(this.desarrolladores == null){
            this.desarrolladores = new ArrayList<>();
        }
        this.desarrolladores.add(desarrollador);
    }
}
