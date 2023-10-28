package com.tccagil.tcc1.domain.trabalhos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrabalhosService {
    
    @Autowired
    private TrabalhosRepository trabalhosRepository;

    public TrabalhosDao obterTrabalhoPorId(Long idtrab) {
        return trabalhosRepository.findById(idtrab).orElse(null);
    }
}
