package com.informatorio.gamedevtaskmanager.service.desarrollador.impl;

import com.informatorio.gamedevtaskmanager.domain.Desarrollador;
import com.informatorio.gamedevtaskmanager.mapper.desarrollador.DesarrolladorMapper;
import com.informatorio.gamedevtaskmanager.model.dto.desarrollador.DesarrolladorDTO;
import com.informatorio.gamedevtaskmanager.repository.desarrollador.DesarrolladorRepository;
import com.informatorio.gamedevtaskmanager.service.desarrollador.DesarrolladorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class DesarrolladorServiceJPAImpl implements DesarrolladorService {

    private final DesarrolladorRepository desarrolladorRepository;
    private final DesarrolladorMapper desarrolladorMapper;

    @Override
    public Optional<Desarrollador> createDesarrollador(DesarrolladorDTO desarrolladorDTO) {

        Optional<Desarrollador> desarrolladorOptional = desarrolladorRepository.findDesarrolladorByEmailAndNombreAllIgnoreCase(
                desarrolladorDTO.getEmail(), desarrolladorDTO.getNombre()
        );

        if (desarrolladorOptional.isPresent()){
            return Optional.empty();
        } else {
            Desarrollador desarrollador = desarrolladorMapper.desarrolladorDTOtoDesarrollador(desarrolladorDTO);
            return Optional.of(desarrolladorRepository.save(desarrollador));
        }
    }
}
