/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tccagil.tcc1.controller;

import com.tccagil.tcc1.domain.trabalhos.TrabalhosDao;
import com.tccagil.tcc1.domain.trabalhos.TrabalhosDto;
import com.tccagil.tcc1.domain.trabalhos.TrabalhosRecord;
import com.tccagil.tcc1.domain.trabalhos.TrabalhosRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author paulo
 */
@Controller
public class TrabalhosController {
    
   @Autowired
    private TrabalhosRepository trabalhosRepository; // Injete o repositório de New_Job_dados

    @GetMapping("/trabalhos")
    public String newJob(HttpSession session, Model model) {
        // Verifica se o usuário está logado
        if (session.getAttribute("usuarioLogado") != null) {
            // Recupera o objeto Login_Cadastro da sessão
            //Login_Cadastro usuarioLogado = (Login_Cadastro) session.getAttribute("usuarioLogado");
            int idUsuario = (int) session.getAttribute("idUsuarioLogado");

            //int idUsuario = usuarioLogado.getId();
            //model.addAttribute("idUsuario", idUsuario);
            // Obtém o nome do usuário e coloca no modelo para ser exibido na página
            //String nomeUsuario = usuarioLogado.getNome(); // Substitua "getNome()" pelo método correto para obter o nome
                                                          // do usuário
            //model.addAttribute("nomeUsuario", nomeUsuario);
            model.addAttribute("idUsuario", idUsuario);

            return "trabalhos";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/trabalhos")
    public String salvarNovoJob(TrabalhosDto dados, HttpSession session, Model model) {
        // Salve o trabalho no banco de dados
        if (session.getAttribute("usuarioLogado") != null) {
        int idusuario_responsavel = (int) session.getAttribute("idUsuarioLogado");
        model.addAttribute("idUsuario", idusuario_responsavel);

            System.out.println(idusuario_responsavel);
        TrabalhosRecord newJobRecord = new TrabalhosRecord(idusuario_responsavel, dados.getTituloTrabalho(), dados.getDescricaoTrabalho(), dados.getData_iniTrabalho(), dados.getPrazo_entregaTrabalho());


        TrabalhosDao newJob = new TrabalhosDao(newJobRecord);
        trabalhosRepository.save(newJob);


        return "redirect:/dash_trabalhos"; // Redirecione para a página de dashboard após a criação do trabalho
            } else {
            return "redirect:/login";
        }
    }
}
