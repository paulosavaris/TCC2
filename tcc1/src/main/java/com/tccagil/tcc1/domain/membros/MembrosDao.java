package com.tccagil.tcc1.domain.membros;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "membrostrab", schema = "tcc")
public class MembrosDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmembros")
    private Long idMembros;

    @Column(name = "nome")
    private String nome;

    @JoinColumn(name = "trabalhoid")
    private Long trabalhoid;

    @JoinColumn(name = "usuarioid")
    private int usuarioid;

    

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "idmembros")
    // private Long idMembros;

    // @Column(name = "nome")
    // private String nome;

    // @ManyToOne
    // @JoinColumn(name = "trabalhoid")
    // private TrabalhosDao trabalhoId;

    // @ManyToOne
    // @JoinColumn(name = "usuarioid")
    // private UsuarioDao usuarioId;

    // public Long getIdMembros() {
    // return idMembros;
    // }

    // public void setIdMembros(Long idMembros) {
    // this.idMembros = idMembros;
    // }

    // public String getNome() {
    // return nome;
    // }

    // public void setNome(String nome) {
    // this.nome = nome;
    // }

    // public TrabalhosDao getTrabalhoId() {
    // return trabalhoId;
    // }

    // public void setTrabalhoId(TrabalhosDao trabalhoId) {
    // this.trabalhoId = trabalhoId;
    // }

    // public UsuarioDao getUsuarioId() {
    // return usuarioId;
    // }

    // public void setUsuarioId(UsuarioDao usuarioId) {
    // this.usuarioId = usuarioId;
    // }

    public Long getIdMembros() {
        return idMembros;
    }



    public void setIdMembros(Long idMembros) {
        this.idMembros = idMembros;
    }



    public String getNome() {
        return nome;
    }



    public void setNome(String nome) {
        this.nome = nome;
    }



    public Long getTrabalhoId() {
        return trabalhoid;
    }



    public void setTrabalhoId(Long trabalhoId) {
        this.trabalhoid = trabalhoId;
    }



    public int getUsuarioId() {
        return usuarioid;
    }



    public void setUsuarioId(int usuarioId) {
        this.usuarioid = usuarioId;
    }



    public MembrosDao() {
    }

}
