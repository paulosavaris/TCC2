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
public class TrabalhosDto {
    private String tituloTrabalho;
    private String descricaoTrabalho;
    private LocalDate data_iniTrabalho;
    private LocalDate prazo_entregaTrabalho;
    
    public String getTituloTrabalho() {
        return tituloTrabalho;
    }
    public String getDescricaoTrabalho() {
        return descricaoTrabalho;
    }
    public LocalDate getData_iniTrabalho() {
        return data_iniTrabalho;
    }
    public LocalDate getPrazo_entregaTrabalho() {
        return prazo_entregaTrabalho;
    }
    public TrabalhosDto(String tituloTrabalho, String descricaoTrabalho, LocalDate data_iniTrabalho,
            LocalDate prazo_entregaTrabalho) {
        this.tituloTrabalho = tituloTrabalho;
        this.descricaoTrabalho = descricaoTrabalho;
        this.data_iniTrabalho = data_iniTrabalho;
        this.prazo_entregaTrabalho = prazo_entregaTrabalho;
        
    }
}
