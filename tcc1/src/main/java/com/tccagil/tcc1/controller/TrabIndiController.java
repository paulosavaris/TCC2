package com.tccagil.tcc1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tccagil.tcc1.Service.AutenticacaoService;
import com.tccagil.tcc1.domain.tarefas.TarefasDao;
import com.tccagil.tcc1.domain.tarefas.TarefasRepository;
import com.tccagil.tcc1.domain.trabalhos.TrabalhosDao;
import com.tccagil.tcc1.domain.trabalhos.TrabalhosService;

import jakarta.servlet.http.HttpSession;

@Controller
public class TrabIndiController {

    @Autowired
    private TrabalhosService trabalhosIndService;

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private TarefasRepository tarefasRepository;

    @GetMapping("/trabalhoIndi/{idtrab}")
    public String trabalhoIndi(@PathVariable Long idtrab, HttpSession session, Model model) {
        // Verifica se o usuário está logado
        if (autenticacaoService.isUsuarioLogado(session)) {
            autenticacaoService.adicionarInformacoesComuns(model, session);
            // List<String> nomesTrabalhos =
            // trabalhoIndiRepository.obterNomesTrabalhosPorUsuario(idUsuario);
            // model.addAttribute("nomesTrabalhos", nomesTrabalhos);
            int idUsuario = (int) session.getAttribute("idUsuarioLogado");
            TrabalhosDao trabalhoIndi = trabalhosIndService.obterTrabalhoPorId(idtrab);
            model.addAttribute("trabalhoIndi", trabalhoIndi);
            model.addAttribute("idtrab", idtrab);
            // Verifica se o trabalho existe e se o usuário logado é o proprietário do
            // trabalho
            if (trabalhoIndi != null && trabalhoIndi.getIdUsuario() == idUsuario) {
                model.addAttribute("trabalhoIndi", trabalhoIndi);

                List<TarefasDao> tarefas = tarefasRepository.obterTarefasPorTrabalho(idtrab);

                // Adicione as tarefas ao modelo
                model.addAttribute("tarefas", tarefas);
                // Redireciona para a página de perfil
                return "trabalhoIndi";
            } else {
                // Trabalho não pertence ao usuário logado, você pode redirecioná-lo para uma
                // página de erro ou fazer outra ação
                return "redirect:/Acesso_negado";
            }
        } else {
            // Caso o usuário não esteja logado, você pode redirecionar para a página de
            // login ou fazer outra ação
            return "redirect:/login";
        }
    }

}
