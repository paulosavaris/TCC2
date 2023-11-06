/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tccagil.tcc1.domain.login;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author paulo
 */
@Repository
@CacheEvict
public interface UsuarioRepository extends JpaRepository<UsuarioDao, Long> {
    UsuarioDao findByEmailAndSenha(String email, String senha);

    UsuarioDao findByEmail(String email);

    boolean existsByEmail(String email);//verifica se um usuário com o email especificado já existe no banco de dados.

    // Método personalizado para buscar e-mails correspondentes ao termo
    @Query("SELECT u.email FROM UsuarioDao u WHERE u.email LIKE %:term%")
    List<String> findEmailsByTerm(String term);

}