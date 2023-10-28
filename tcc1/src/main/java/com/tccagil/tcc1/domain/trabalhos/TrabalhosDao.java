/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tccagil.tcc1.domain.trabalhos;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

/**
 *
 * @author paulo
 */
@Entity
@Table(name = "trabalho", schema = "tcc")
@Cacheable(false)
public class TrabalhosDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) /* estratégia de geração de chave identity */
     @Column(name = "idtrab")
    private int idTrab;

    public int getidTrab() {
        return idTrab;
    }
    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descricao")
    private String descricao;
    
    @Column(name = "datadeinicio")
    private LocalDate dataDeInicio;
    
    @Column(name = "prazodeentrega")
    private LocalDate prazoDeEntrega;
    
    @Column(name = "idusuarioresponsavel")
    private int idUsuarioResponsavel;

    public TrabalhosDao(){
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public LocalDate getDataDeInicio() {
        return dataDeInicio;
    }

    public LocalDate getPrazoDeEntrega() {
        return prazoDeEntrega;
    }

    public int getIdUsuario() {
        return idUsuarioResponsavel;
    }

    public TrabalhosDao(TrabalhosRecord dados) {
        this.titulo = dados.tituloTrabalho();
        this.descricao = dados.descricaoTrabalho();
        this.dataDeInicio = dados.data_iniTrabalho();
        this.prazoDeEntrega = dados.prazo_entregaTrabalho();
        this.idUsuarioResponsavel = dados.idusuario_responsavel();
    }

    @Override
    public String toString() {
        return "New_Job_dados [id_trab=" + idTrab + ", titulo=" + titulo + ", descricao=" + descricao + ", data_ini="
                + dataDeInicio + ", data_prazo=" + prazoDeEntrega + "]";
    }
        
}
