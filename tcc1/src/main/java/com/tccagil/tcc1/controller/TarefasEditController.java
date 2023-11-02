package com.tccagil.tcc1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tccagil.tcc1.Service.AutenticacaoService;
import com.tccagil.tcc1.domain.tarefas.TarefasDao;
import com.tccagil.tcc1.domain.tarefas.TarefasRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class TarefasEditController {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private TarefasRepository tarefasRepository;

    @GetMapping("/tarefasEdit/{idtarefas}")
    public String exibirTarefa(@PathVariable Long idtarefas, HttpSession session, Model model) {
        if (autenticacaoService.isUsuarioLogado(session)) {
            autenticacaoService.adicionarInformacoesComuns(model, session);

            TarefasDao tarefa = tarefasRepository.findById(idtarefas).orElse(null);
            model.addAttribute("tarefa", tarefa);

            model.addAttribute("idtarefas", idtarefas);
            return "tarefasEdit";
        } else {
            return "redirect:/login";
        }
    }

}
