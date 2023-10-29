package com.tccagil.tcc1.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tccagil.tcc1.domain.login.UsuarioDao;
import com.tccagil.tcc1.domain.trabalhos.TrabalhosDao;
import com.tccagil.tcc1.domain.trabalhos.TrabalhosService;

import jakarta.servlet.http.HttpSession;

@Controller
public class TrabIndiController {

    @Autowired
    private TrabalhosService trabalhosIndService;

    @GetMapping("/trabalhoIndi/{idtrab}")
    public String trabalhoIndi(@PathVariable Long idtrab, HttpSession session, Model model) {
        // Verifica se o usuário está logado
        if (session.getAttribute("usuarioLogado") != null) {
            // Recupera o objeto Login_Cadastro da sessão
            UsuarioDao usuarioLogado = (UsuarioDao) session.getAttribute("usuarioLogado");
            int idUsuario = (int) session.getAttribute("idUsuarioLogado");         

            // Obtém o nome do usuário e coloca no modelo para ser exibido na página
            String nomeUsuario = usuarioLogado.getNome(); // Substitua "getNome()" pelo método correto para obter o nome do usuário
            model.addAttribute("idUsuario", idUsuario);
            model.addAttribute("nomeUsuario", nomeUsuario);
            
            // List<String> nomesTrabalhos = trabalhoIndiRepository.obterNomesTrabalhosPorUsuario(idUsuario);
            // model.addAttribute("nomesTrabalhos", nomesTrabalhos);

            TrabalhosDao trabalho = trabalhosIndService.obterTrabalhoPorId(idtrab);
            model.addAttribute("trabalho", trabalho);
            // Redireciona para a página de perfil
            return "trabalhoIndi";
        } else {
            // Caso o usuário não esteja logado, você pode redirecionar para a página de login ou fazer outra ação
            return "redirect:/login";
        }
    }
}
