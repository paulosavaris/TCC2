package com.tccagil.tcc1.domain.log;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "log", schema = "tcc")
public class LogDao {
    
    @Column(name = "idmembro")
private int idmembro;

    @Id
    @Column(name = "idlog")
private int idlog;

    @Column(name = "tipoevento")
private String tipoevento;

    @Column(name = "descricaoevento")
private String descricaoevento;

    @Column(name = "datahoraevento")
private LocalDateTime datahoraevento;

    @Column(name = "idusuariolog")
private int idusuariolog;

    @Column(name = "idtrabalho")
private int idtrabalho;

    @Column(name = "idtarefa")
private int idtarefa;

    public int getIdmembro() {
        return idmembro;
    }

    public void setIdmembro(int idmembro) {
        this.idmembro = idmembro;
    }

    public int getIdlog() {
        return idlog;
    }

    public void setIdlog(int idlog) {
        this.idlog = idlog;
    }

    public String getTipoevento() {
        return tipoevento;
    }

    public void setTipoevento(String tipoevento) {
        this.tipoevento = tipoevento;
    }

    public String getDescricaoevento() {
        return descricaoevento;
    }

    public void setDescricaoevento(String descricaoevento) {
        this.descricaoevento = descricaoevento;
    }

    public LocalDateTime getDatahoraevento() {
        return datahoraevento;
    }

    public void setDatahoraevento(LocalDateTime datahoraevento) {
        this.datahoraevento = datahoraevento;
    }

    public int getIdusuariolog() {
        return idusuariolog;
    }

    public void setIdusuariolog(int idusuariolog) {
        this.idusuariolog = idusuariolog;
    }

    public int getIdtrabalho() {
        return idtrabalho;
    }

    public void setIdtrabalho(int idtrabalho) {
        this.idtrabalho = idtrabalho;
    }

    public int getIdtarefa() {
        return idtarefa;
    }

    public void setIdtarefa(int idtarefa) {
        this.idtarefa = idtarefa;
    }
    


}
