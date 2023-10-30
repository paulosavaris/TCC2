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

}
