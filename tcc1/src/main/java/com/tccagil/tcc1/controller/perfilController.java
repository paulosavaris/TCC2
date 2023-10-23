/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tccagil.tcc1.controller;

import com.tccagil.tcc1.domain.login.UsuarioDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;



import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

@Controller
public class perfilController {
    
    @GetMapping("/perfil")
    public String perfil(HttpSession session, Model model) {
        // Verifica se o usuário está logado
        if (session.getAttribute("usuarioLogado") != null) {
            // Recupera o objeto Login_Cadastro da sessão
            UsuarioDao usuarioLogado = (UsuarioDao) session.getAttribute("usuarioLogado");
            
            // Obtém o nome do usuário e coloca no modelo para ser exibido na página
            String nomeUsuario = usuarioLogado.getNome(); // Substitua "getNome()" pelo método correto para obter o nome do usuário
            model.addAttribute("nomeUsuario", nomeUsuario);
            
            // Redireciona para a página de perfil
            return "perfil";
        } else {
            // Caso o usuário não esteja logado, você pode redirecionar para a página de login ou fazer outra ação
            return "redirect:/login";
        }
    }
}
  /*  public String perfil( @RequestParam (name = "activeSection", required = false) String activeSection, Model model){
        model.addAttribute("activeSection", activeSection);
        return "perfil";
    }*/
