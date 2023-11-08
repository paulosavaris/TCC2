package com.tccagil.tcc1.domain.log;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<LogDao, Long> {

    @Query(value = "SELECT to_char(l.datahoraevento, 'dd/MM/yyyy hh24:mi'), l FROM tcc.log l " +
            "LEFT JOIN tcc.trabalho t ON t.idtrab = l.idtrabalho " +
            "LEFT JOIN tcc.tarefas ta ON ta.idtarefas = l.idtarefa " +
            "LEFT JOIN tcc.membrostrab m ON m.trabalhoid = t.idtrab " +
            "WHERE l.idusuariolog = :idUsuario OR m.usuarioid = :idUsuario " +
            " and idlog > (select min(idlog) from tcc.log where log.idusuariolog = :idUsuario)"+
            "ORDER BY l.idlog DESC", nativeQuery = true)
    List<Object[]> findLogsByUserIdOrMemberUserId(@Param("idUsuario") int idUsuario);

}
