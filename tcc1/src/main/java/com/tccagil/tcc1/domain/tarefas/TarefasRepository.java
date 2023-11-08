package com.tccagil.tcc1.domain.tarefas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefasRepository extends JpaRepository<TarefasDao, Long>{
    
    @Query("SELECT ta from TarefasDao ta where ta.trabalhoid = :idtrab")
    List<TarefasDao> obterTarefasPorTrabalho(@Param("idtrab") Long idtrab);

    @Query("Select ta, tb from TarefasDao ta inner join TrabalhosDao tb on tb.idTrab = ta.trabalhoid" + 
    " Inner join MembrosDao m ON m.trabalhoid = tb.idTrab Where tb.idUsuarioResponsavel = :idUsuario OR m.usuarioid = :idUsuario")
    List<Object[]> obterTarefasPorUsuario(@Param("idUsuario") int idUsuario);

    @Query("Select COUNT(DISTINCT ta.idTarefas) as tarefas, COUNT(DISTINCT tb.idTrab) as trabalhos from TarefasDao ta inner join TrabalhosDao tb on tb.idTrab = ta.trabalhoid" + 
    " Inner join MembrosDao m ON m.trabalhoid = tb.idTrab Where tb.idUsuarioResponsavel = :idUsuario OR m.usuarioid = :idUsuario")
    List<Object[]> obterQuantidadeTarefaseTrabPorUsuario(@Param("idUsuario") int idUsuario);

}
