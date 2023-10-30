package com.tccagil.tcc1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tccagil.tcc1.Service.AutenticacaoService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AcessoNegado {
    @Autowired
    private AutenticacaoService autenticacaoService;

    @GetMapping("/Acesso_negado")
    public String perfil(HttpSession session, Model model) {
        if (autenticacaoService.isUsuarioLogado(session)) {
            autenticacaoService.adicionarInformacoesComuns(model, session);
            return "Acesso_negado";
        } else {
            return "redirect:/login";
        }
    }
}
