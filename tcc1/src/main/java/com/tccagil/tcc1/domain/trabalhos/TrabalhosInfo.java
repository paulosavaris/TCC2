package com.tccagil.tcc1.domain.trabalhos;

public class TrabalhosInfo {
    private int idTrab;
    private String titulo;

    public TrabalhosInfo(int idTrab, String titulo) {
        this.idTrab = idTrab;
        this.titulo = titulo;
    }

    public int getIdTrab() {
        return idTrab;
    }

    public String getTitulo() {
        return titulo;
    }
}
