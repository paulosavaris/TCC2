package com.tccagil.tcc1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tccagil.tcc1.Service.AutenticacaoService;
import com.tccagil.tcc1.domain.tarefas.TarefasDao;
import com.tccagil.tcc1.domain.tarefas.TarefasRecord;
import com.tccagil.tcc1.domain.tarefas.TarefasRepository;
import com.tccagil.tcc1.domain.trabalhos.TrabalhosDao;
import com.tccagil.tcc1.domain.trabalhos.TrabalhosService;

import jakarta.servlet.http.HttpSession;
@Controller
public class TarefasCadastroController {

    @Autowired
    private TarefasRepository tarefasRepository;

    @Autowired
    private TrabalhosService trabalhosIndService;

    @Autowired
    private AutenticacaoService autenticacaoService;

    @GetMapping("/trabalhoIndi/{idtrab}/tarefasCad")
    public String novaTarefa(@PathVariable Long idtrab, HttpSession session, Model model) {
        if (autenticacaoService.isUsuarioLogado(session)) {
            autenticacaoService.adicionarInformacoesComuns(model, session);
                    int idUsuario = (int) session.getAttribute("idUsuarioLogado");

            TrabalhosDao trabalhoIndi = trabalhosIndService.obterTrabalhoPorId(idtrab);
            model.addAttribute("idtrab", idtrab);

            // Verifica se o trabalho existe e se o usuário logado é o proprietário do trabalho
            if (trabalhoIndi != null && trabalhoIndi.getIdUsuario() == idUsuario) {
                model.addAttribute("trabalhoIndi", trabalhoIndi);
                return "tarefasCad"; // Página para criar uma nova tarefa
            } else {
                return "redirect:/Acesso_negado";
            }
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/trabalhoIndi/{idtrab}/tarefasCad")
    public String salvarNovaTarefa(@PathVariable Long idtrab, TarefasRecord dados) {
        // Lógica para salvar a nova tarefa no banco de dados
        TarefasRecord tarefas = new TarefasRecord(idtrab, dados.tituloTarefas(),
        dados.descricaoTarefas(), dados.prioridadeTarefas(), dados.statusTarefas());

        var tarefasCad = new TarefasDao(tarefas);
        // userCadaTeste.add(userCadastro);
        tarefasRepository.save(tarefasCad);

        
        // Redireciona de volta para a página de trabalho relacionada
        return "redirect:/trabalhoIndi/" + idtrab;
    }
}
