/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tccagil.tcc1.domain.trabalhos;

import java.time.LocalDate;

/**
 *
 * @author paulo
 */
public record TrabalhosRecord (int idusuario_responsavel, String tituloTrabalho, String descricaoTrabalho,
                LocalDate data_iniTrabalho,
                LocalDate prazo_entregaTrabalho){
    
}
