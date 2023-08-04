package com.informatorio.gamedevtaskmanager.domain;

import com.informatorio.gamedevtaskmanager.enumeration.EstadoEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Tarea {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(updatable = false, nullable = false, length = 36, columnDefinition = "varchar(36)")
    private UUID id;

    @Column(length = 300, columnDefinition = "varchar(300)", updatable = true, nullable = false)
    private String descripcion;

    @ManyToOne
    private Juego juego;

    @ManyToOne
    private Desarrollador desarrolladorResponsable;

    @Column(updatable = true, nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate fechaLimite;

    @Enumerated(EnumType.STRING)
    private EstadoEnum estado;

    public void setJuego(Juego juego){
        this.juego = juego;
        juego.getTareas().add(this);
    }
}
