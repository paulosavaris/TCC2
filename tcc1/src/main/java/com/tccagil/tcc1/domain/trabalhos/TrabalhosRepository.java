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
    
    // @Query("SELECT t.titulo FROM TrabalhosDao t WHERE t.idUsuarioResponsavel  = :idUsuario")
    // List<String> obterNomesTrabalhosPorUsuario(@Param("idUsuario") int idUsuario);
    // @Query("SELECT t FROM TrabalhosDao t WHERE t.idUsuarioResponsavel = :idUsuario")
    // List<TrabalhosDao> obterTrabalhosPorUsuario(@Param("idUsuario") int idUsuario);
    @Query("SELECT t FROM TrabalhosDao t  LEFT JOIN MembrosDao m ON m.trabalhoid = t.idTrab WHERE t.idUsuarioResponsavel = :idUsuario OR m.usuarioid = :idUsuario")
    List<TrabalhosDao> obterTrabalhosPorUsuario(@Param("idUsuario") int idUsuario);


    @Query("SELECT t.idTrab, t.titulo, t.descricao, count(distinct ta.idTarefas), count(distinct m.idMembros), u.nome FROM TrabalhosDao t " +
    "LEFT JOIN MembrosDao m ON m.trabalhoid = t.idTrab " +
    "LEFT JOIN TarefasDao ta ON ta.trabalhoid = t.idTrab " +
    "LEFT JOIN UsuarioDao u on u.idUsuario = t.idUsuarioResponsavel " +
    "WHERE t.idUsuarioResponsavel = :idUsuario OR m.usuarioid = :idUsuario group by 1, 2, 3, 6")
    List<Object[]> obterInfoTrabPorUser(@Param("idUsuario") int idUsuario);

}
