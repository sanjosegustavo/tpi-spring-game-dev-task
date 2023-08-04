package com.informatorio.gamedevtaskmanager.repository.juego;

import com.informatorio.gamedevtaskmanager.domain.Juego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JuegoRepository extends JpaRepository<Juego, UUID> {
    Optional<Juego> findJuegoByTituloEqualsIgnoreCase(String titulo);

    @Query("select j from Juego j where j.fechaLanzamiento <= :fecha")
    List<Juego> findByFinalizados(@Param("fecha") LocalDate fecha);

    @Query("select j from Juego j where j.fechaLanzamiento > :fecha")
    List<Juego> findByNoFinalizados(@Param("fecha") LocalDate fecha);

}
