package com.tccagil.tcc1.domain.tarefas;

public class TarefasDTO {
    private String TarefaNomeEdit;
    private String TarefaDescricaoEdit;
    private String TarefaPrioridadeEdit;
    private String statusTarefasEdit;

    public String getTarefaNomeEdit() {
        return TarefaNomeEdit;
    }
    public void setTarefaNomeEdit(String tarefaNomeEdit) {
        TarefaNomeEdit = tarefaNomeEdit;
    }
    public String getTarefaDescricaoEdit() {
        return TarefaDescricaoEdit;
    }
    public void setTarefaDescricaoEdit(String tarefaDescricaoEdit) {
        TarefaDescricaoEdit = tarefaDescricaoEdit;
    }
    public String getTarefaPrioridadeEdit() {
        return TarefaPrioridadeEdit;
    }
    public void setTarefaPrioridadeEdit(String tarefaPrioridadeEdit) {
        TarefaPrioridadeEdit = tarefaPrioridadeEdit;
    }
    public String getStatusTarefasEdit() {
        return statusTarefasEdit;
    }
    public void setStatusTarefasEdit(String statusTarefasEdit) {
        this.statusTarefasEdit = statusTarefasEdit;
    }

    // Getters e setters

}
