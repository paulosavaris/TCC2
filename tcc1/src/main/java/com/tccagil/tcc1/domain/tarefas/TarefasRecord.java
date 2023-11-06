package com.tccagil.tcc1.domain.tarefas;

public record TarefasRecord(Long trabalhoIdTarefas, String tituloTarefas,
        String descricaoTarefas, String prioridadeTarefas, String statusTarefas, int idUsuarioResponsavel, int idUsuarioResponsavel_UP) {

}
