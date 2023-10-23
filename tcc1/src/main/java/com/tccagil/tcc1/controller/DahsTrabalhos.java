/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tccagil.tcc1.controller;

import com.tccagil.tcc1.domain.login.UsuarioDao;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author paulo
 */
@Controller
public class DahsTrabalhos {
    @GetMapping("/dash_trabalhos")
    public String perfil(HttpSession session, Model model) {
        // Verifica se o usuário está logado
        if (session.getAttribute("usuarioLogado") != null) {
            // Recupera o objeto Login_Cadastro da sessão
            UsuarioDao usuarioLogado = (UsuarioDao) session.getAttribute("usuarioLogado");
            
            // Obtém o nome do usuário e coloca no modelo para ser exibido na página
            String nomeUsuario = usuarioLogado.getNome(); // Substitua "getNome()" pelo método correto para obter o nome do usuário
            model.addAttribute("nomeUsuario", nomeUsuario);
            
            // Redireciona para a página de perfil
            return "dash_trabalhos";
        } else {
            // Caso o usuário não esteja logado, você pode redirecionar para a página de login ou fazer outra ação
            return "redirect:/login";
        }
    }
}
