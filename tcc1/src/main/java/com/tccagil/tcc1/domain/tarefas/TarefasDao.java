package com.tccagil.tcc1.domain.tarefas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tarefas", schema = "tcc")
public class TarefasDao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtarefas")
    private int idTarefas;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "prioridade")
    private String prioridade;

    @Column(name = "status")
    private String status;

    @Column(name = "trabalhoid")
    private Long trabalhoid;

    
    public int getIdTarefas() {
        return idTarefas;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public String getStatus() {
        return status;
    }

    public long getTrabalhoid() {
        return trabalhoid;
    }

    public TarefasDao(TarefasRecord dados) {
        this.titulo = dados.tituloTarefas();
        this.descricao = dados.descricaoTarefas();
        this.prioridade = dados.prioridadeTarefas();
        this.status = dados.statusTarefas();
        this.trabalhoid = dados.trabalhoIdTarefas();
    }


    
}
