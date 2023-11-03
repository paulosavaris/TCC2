package com.tccagil.tcc1.domain.membros;

public class MembrosDTO {
    private String email;
    private String nome;

    public MembrosDTO(String email, String nome) {
        this.email = email;
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }
}
