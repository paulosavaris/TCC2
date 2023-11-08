/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tccagil.tcc1.controller;

import com.tccagil.tcc1.Service.AutenticacaoService;
import com.tccagil.tcc1.domain.trabalhos.TrabalhosRepository;

import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author paulo
 */
@Controller
public class DahsTrabalhos {


        @Autowired
    private AutenticacaoService autenticacaoService;

@Autowired
    private TrabalhosRepository trabalhosRepository;

    @GetMapping("/dash_trabalhos")
    public String perfil(HttpSession session, Model model)  {
        if (autenticacaoService.isUsuarioLogado(session)) {
            autenticacaoService.adicionarInformacoesComuns(model, session);

            int idUsuario = (int) session.getAttribute("idUsuarioLogado");

            List<Object[]> trabalhos = trabalhosRepository.obterInfoTrabPorUser(idUsuario);
            model.addAttribute("trabalhosInfo", trabalhos);



            return "dash_trabalhos";
        } else {
            return "redirect:/login";
        }
    }
}
