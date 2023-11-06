/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tccagil.tcc1.domain.login;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
/**
 *
 * @author paulo
 */

@Entity
@Table(name = "usuario", schema = "tcc")
@Cacheable(false)
public class UsuarioDao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) /* estratégia de geração de chave identity */
    @Column(name = "idusuario")
    private int idUsuario;

    public int getIdUsuario() {
        return idUsuario;
    }

    private String nome;
    
    @Column(name = "email", unique = true)
    private String email;
    private String senha;
    // private String confirmaSenha;

    // Construtor padrão sem argumentos
    // permitirá que o Hibernate crie instâncias da classe Login_Cadastro quando
    // necessário durante as operações de consulta e persistência.
    public UsuarioDao() {
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
    /*
     * public String getConfirmaSenha() {
     * return confirmaSenha;
     * }
     */

    public UsuarioDao(UsuarioRecord dados) {
        this.nome = dados.CadastroNome();
        this.email = dados.CadastroEmail();
        this.senha = dados.CadastroPassword();
        // this.confirmaSenha = dados.CadastroConfirmPassword();
    }

    @Override
    public String toString() {
        return "Cadastro {" + "nome='" + nome + '\'' +
                ", email='" + email +
                ", senha='" + senha + '\'' + '}';
    }
    
}
