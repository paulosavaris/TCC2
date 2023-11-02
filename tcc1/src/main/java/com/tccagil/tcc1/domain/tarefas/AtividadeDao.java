package com.tccagil.tcc1.domain.tarefas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "atividadetarefas", schema = "tcc")
public class AtividadeDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idatividade")
    private int idAtividade;

    @Column(name = "usuarioid")
    private int usuarioId;

    @Column(name = "tarefaid")
    private long tarefaId;

    @Column(name = "descricao")
    private String descricao;

    public int getIdAtividade() {
        return idAtividade;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public long getTarefaId() {
        return tarefaId;
    }

    public String getDescricao() {
        return descricao;
    }

    public AtividadeDao(){}//construtor vazio

    public AtividadeDao(AtividadesRecord dados) {
        this.usuarioId = dados.usuarioIdAtividade();
        this.tarefaId = dados.tarefaIdAtividade();
        this.descricao = dados.descricaoAtividade();
    }

}
