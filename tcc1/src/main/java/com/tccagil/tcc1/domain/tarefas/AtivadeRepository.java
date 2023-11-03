package com.tccagil.tcc1.domain.tarefas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AtivadeRepository extends JpaRepository<AtividadeDao, Long> {

    // @Query("SELECT ad FROM AtividadeDao ad JOIN UsuarioDao u ON ad.usuarioId = u.idUsuario WHERE ad.tarefaId = :idtarefas")
    // List<AtividadeDao> obterAtividadePorTarefa(@Param("idtarefas") Long idtarefas);
    @Query("SELECT u.nome as nome, ad FROM AtividadeDao ad JOIN UsuarioDao u ON ad.usuarioId = u.idUsuario WHERE ad.tarefaId = :idtarefas")
    List<Object[]> obterAtividadePorTarefa(@Param("idtarefas") Long idtarefas);
}
