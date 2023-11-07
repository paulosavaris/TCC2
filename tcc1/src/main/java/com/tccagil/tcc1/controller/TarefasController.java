package com.tccagil.tcc1.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tccagil.tcc1.Service.AutenticacaoService;
import com.tccagil.tcc1.domain.membros.MembrosDao;
import com.tccagil.tcc1.domain.membros.MembrosService;
import com.tccagil.tcc1.domain.tarefas.TarefasDao;
import com.tccagil.tcc1.domain.tarefas.TarefasRepository;
import com.tccagil.tcc1.domain.trabalhos.TrabalhosDao;
import com.tccagil.tcc1.domain.trabalhos.TrabalhosService;

import jakarta.servlet.http.HttpSession;

@Controller
public class TarefasController {

    @Autowired
    private TrabalhosService trabalhosIndService;

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private TarefasRepository tarefasRepository;

    @Autowired
    private MembrosService membrosService;

    @GetMapping("/tarefas")
    public String tarefas(HttpSession session, Model model) {
        // Verifica se o usuário está logado
        if (autenticacaoService.isUsuarioLogado(session)) {
            autenticacaoService.adicionarInformacoesComuns(model, session);

            int idUsuario = (int) session.getAttribute("idUsuarioLogado");

            List<TarefasDao> tarefas = tarefasRepository.obterTarefasPorUsuario(idUsuario);
            model.addAttribute("tarefas", tarefas);

            return "tarefas";
        } else {
            return "redirect:/login";
        }

    }

}
