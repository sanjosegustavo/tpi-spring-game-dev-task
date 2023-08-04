package com.informatorio.gamedevtaskmanager.repository.desarrollador;

import com.informatorio.gamedevtaskmanager.domain.Desarrollador;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DesarrolladorRepository extends JpaRepository<Desarrollador, UUID> {

    Optional<Desarrollador> findDesarrolladorByEmailAndNombreAllIgnoreCase(String email, String nombre);
}
