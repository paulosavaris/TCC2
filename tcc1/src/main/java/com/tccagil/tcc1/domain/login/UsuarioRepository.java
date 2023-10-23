/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tccagil.tcc1.domain.login;


import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author paulo
 */
@Repository
@CacheEvict
public interface UsuarioRepository extends JpaRepository<UsuarioDao, Long>{
    UsuarioDao findByEmailAndSenha(String email, String senha);
}
