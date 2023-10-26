/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tccagil.tcc1.domain.trabalhos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author paulo
 */
public interface TrabalhosRepository extends JpaRepository<TrabalhosDao, Long>{
    
    @Query("SELECT t.titulo FROM TrabalhosDao t WHERE t.idUsuarioResponsavel  = :idUsuario")
    List<String> obterNomesTrabalhosPorUsuario(@Param("idUsuario") int idUsuario);

}
