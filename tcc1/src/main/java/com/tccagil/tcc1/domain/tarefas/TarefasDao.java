package com.tccagil.tcc1.domain.tarefas;

import java.util.Objects;

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

    @Column(name = "idusuarioresponsavel")
    private int idUsuarioResponsavel;

    @Column(name = "idusuarioresponsavelUp")
    private int idUsuarioResponsavel_UP;

    
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

    public TarefasDao(){}

    public TarefasDao(TarefasRecord dados) {
        this.titulo = dados.tituloTarefas();
        this.descricao = dados.descricaoTarefas();
        this.prioridade = dados.prioridadeTarefas();
        this.status = dados.statusTarefas();
        this.trabalhoid = dados.trabalhoIdTarefas();
        this.idUsuarioResponsavel = dados.idUsuarioResponsavel();
        this.idUsuarioResponsavel_UP = dados.idUsuarioResponsavel_UP();
    }

    public boolean isEqual(TarefasDTO form) {//para comparar se os campos da entidade são iguais aos campos do objeto TarefasDTO
        return Objects.equals(this.titulo, form.getTarefaNomeEdit()) &&
        Objects.equals(this.descricao, form.getTarefaDescricaoEdit()) &&
        Objects.equals(this.prioridade, form.getTarefaPrioridadeEdit()) &&
        Objects.equals(this.status, form.getStatusTarefasEdit());
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdUsuarioResponsavel() {
        return idUsuarioResponsavel;
    }

    public void setIdUsuarioResponsavel(int idUsuarioResponsavel) {
        this.idUsuarioResponsavel = idUsuarioResponsavel;
    }

    public int getIdUsuarioResponsavel_UP() {
        return idUsuarioResponsavel_UP;
    }

    public void setIdUsuarioResponsavel_UP(int idUsuarioResponsavel_UP) {
        this.idUsuarioResponsavel_UP = idUsuarioResponsavel_UP;
    }


    
}
