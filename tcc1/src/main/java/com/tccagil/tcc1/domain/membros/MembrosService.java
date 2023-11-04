package com.tccagil.tcc1.domain.membros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembrosService {
    
    @Autowired
    private MembrosRepository membrosRepository;

    public MembrosDao obterMembroIDporTrabalho(Long idtrab, int idUsuario){
        // return membrosRepository.findByTrabalhoIdAndUsuarioId(idtrab, idUsuario);
        return membrosRepository.findByUsuarioidAndTrabalhoid(idUsuario, idtrab);
    }
}
