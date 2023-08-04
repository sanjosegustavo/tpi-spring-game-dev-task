package com.informatorio.gamedevtaskmanager.domain;
import com.informatorio.gamedevtaskmanager.enumeration.RolEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Desarrollador {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(updatable = false, nullable = false, length = 36, columnDefinition = "varchar(36)")
    private UUID id;

    @Column(length = 100, columnDefinition = "varchar(100)", updatable = true, nullable = false)
    private String nombre;

    @Column(length = 100, columnDefinition = "varchar(100)", updatable = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private RolEnum rol;

}
