package com.informatorio.gamedevtaskmanager.service.juego.impl;

import com.informatorio.gamedevtaskmanager.domain.Desarrollador;
import com.informatorio.gamedevtaskmanager.domain.Juego;
import com.informatorio.gamedevtaskmanager.exception.NotFoundException;
import com.informatorio.gamedevtaskmanager.mapper.juego.JuegoDevsMapper;
import com.informatorio.gamedevtaskmanager.mapper.juego.JuegoMapper;
import com.informatorio.gamedevtaskmanager.model.dto.juego.JuegoDTO;
import com.informatorio.gamedevtaskmanager.model.dto.juego.JuegoDevsDTO;
import com.informatorio.gamedevtaskmanager.repository.desarrollador.DesarrolladorRepository;
import com.informatorio.gamedevtaskmanager.repository.juego.JuegoRepository;
import com.informatorio.gamedevtaskmanager.service.juego.JuegoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@AllArgsConstructor
public class JuegoServiceJPAImpl implements JuegoService {

    private final JuegoRepository juegoRepository;
    private final JuegoMapper juegoMapper;
    private final DesarrolladorRepository desarrolladorRepository;
    private final JuegoDevsMapper juegoDevsMapper;

    @Override
    public Optional<Juego> createJuego(JuegoDTO juegoDTO) {

        Optional<Juego> juegoOptional = juegoRepository.findJuegoByTituloEqualsIgnoreCase(juegoDTO.getTitulo());

        if (juegoOptional.isPresent()){
            return Optional.empty();
        } else {
            Juego juego = juegoMapper.juegoDTOToJuego(juegoDTO);
            return Optional.of(juegoRepository.save(juego));
        }

    }

    @Override
    public Optional<Juego> updateJuego(UUID idJuego, UUID idDesarrollador) throws NotFoundException {

        Optional<Juego> juegoOptional = juegoRepository.findById(idJuego);
        Optional<Desarrollador> desarrolladorOptional = desarrolladorRepository.findById(idDesarrollador);

        if (juegoOptional.isEmpty() || desarrolladorOptional.isEmpty()){
            throw new NotFoundException();
        }

        updatingJuego(juegoOptional.get(), desarrolladorOptional.get());
        return Optional.of(juegoRepository.save(juegoOptional.get()));
    }

    @Override
    public List<JuegoDTO> getJuegos(Boolean finalizados, Boolean noFinalizados) {
        List<Juego> juegos = juegoRepository.findAll();
        List<JuegoDTO> juegoDTOS = new ArrayList<>();

        if (finalizados && noFinalizados){
            juegos = juegoRepository.findAll();
        } else if (finalizados) {
            juegos = juegoRepository.findByFinalizados(LocalDate.now());
        } else if (noFinalizados) {
            juegos = juegoRepository.findByNoFinalizados(LocalDate.now());
        } else {
            juegos = juegoRepository.findAll();
        }

        for (Juego juego : juegos) {
            juegoDTOS.add(juegoMapper.juegoToJuegoDTO(juego));
        }

        return juegoDTOS;
    }

    @Override
    public Optional<JuegoDevsDTO> getJuegosDevsById(UUID idJuego) {
        Optional<Juego> juegoOptional = juegoRepository.findById(idJuego);

        if (juegoOptional.isPresent()){
            return Optional.of(juegoDevsMapper.juegoToJuegoDevsDTO(juegoOptional.get()));
        }
        return Optional.empty();
    }


    private void updatingJuego(Juego juego, Desarrollador desarrolladorUpdated){
        boolean desarrolladorYaIncluido = false;

        for (Desarrollador desarrollador : juego.getDesarrolladores()) {
            if (desarrollador.getId() == desarrolladorUpdated.getId()){
                desarrolladorYaIncluido = true;
                break;
            }
        }
        if (!desarrolladorYaIncluido){
            juego.getDesarrolladores().add(desarrolladorUpdated);
        }

    }
}
