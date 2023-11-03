package com.tccagil.tcc1.domain.membros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;




@Repository
public interface MembrosRepository extends JpaRepository<MembrosDao, Long> {



    @Query("SELECT u.email as email, m FROM MembrosDao m JOIN UsuarioDao u ON m.usuarioid = u.idUsuario WHERE m.trabalhoid = :idtrab")
    List<Object[]> obterMembroPorTrabalho(@Param("idtrab") Long idtrab);
}

