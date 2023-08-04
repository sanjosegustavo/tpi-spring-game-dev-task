package com.informatorio.gamedevtaskmanager.repository.tarea;

import com.informatorio.gamedevtaskmanager.domain.Tarea;
import com.informatorio.gamedevtaskmanager.enumeration.EstadoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, UUID> {

    @Query("select t from Tarea t where t.desarrolladorResponsable.id = :idDesarrollador")
    List<Tarea> findByDesarrollador(@Param("idDesarrollador") UUID idDesarrollador);

    @Query("select t from Tarea t where t.estado = :estado")
    List<Tarea> findByEstado(@Param("estado") EstadoEnum estado);

    @Query("select t from Tarea t where t.fechaLimite = :fechaLimite")
    List<Tarea> findByFechaLimite(@Param("fechaLimite") LocalDate fechaLimite);

    @Query("select t from Tarea t where t.fechaLimite < :fechaActual and t.estado != COMPLETADA")
    List<Tarea> findByAtrasadas(@Param("fechaActual") LocalDate fechaActual);

    @Query("select t from Tarea t where t.juego.id = :idJuego")
    List<Tarea> findByJuego(@Param("idJuego") UUID idJuego);
}
