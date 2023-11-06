package com.tccagil.tcc1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tccagil.tcc1.Service.AutenticacaoService;
import com.tccagil.tcc1.domain.membros.MembrosDao;
import com.tccagil.tcc1.domain.membros.MembrosService;
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

    @Autowired
    private MembrosService membrosService;

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
            MembrosDao membrosInd = membrosService.obterMembroIDporTrabalho(idtrab, idUsuario);

            model.addAttribute("trabalhoIndi", trabalhoIndi);
            model.addAttribute("membrosInd", membrosInd);
            model.addAttribute("idtrab", idtrab);
            // Verifica se o trabalho existe e se o usuário logado é o proprietário do
            // trabalho

            if (trabalhoIndi.getIdUsuario() == idUsuario){
                boolean isButtonVisible = true; // Defina como true se o botão deve ser visível

                model.addAttribute("isButtonVisible", isButtonVisible);
            } else{
                boolean isButtonVisible = false;
                model.addAttribute("isButtonVisible", isButtonVisible);
            }

            if (trabalhoIndi != null && (trabalhoIndi.getIdUsuario() == idUsuario
                    || (membrosInd != null && membrosInd.getUsuarioId() == idUsuario))) {
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

    @PostMapping("/trabalhoIndi/{idtrab}/removeTrabalho")
    @ResponseBody
    public ResponseEntity<String> excluirTrabalho(@PathVariable Long idtrab, HttpSession session) {
        if (autenticacaoService.isUsuarioLogado(session)) {
            // Verifique se o usuário logado é o proprietário do trabalho
            int idUsuarioLogado = (int) session.getAttribute("idUsuarioLogado");
            TrabalhosDao trabalhoIndi = trabalhosIndService.obterTrabalhoPorId(idtrab);
            if (trabalhoIndi != null && trabalhoIndi.getIdUsuario() == idUsuarioLogado) {
                // O usuário logado é o proprietário do trabalho, então você pode excluir o
                // trabalho aqui
                // Lógica para excluir o trabalho
                trabalhosIndService.excluirTrabalho(idtrab); // Exemplo de como excluir um trabalho (ajuste conforme sua
                                                             // implementação)
                return new ResponseEntity<>("Trabalho removido com sucesso", HttpStatus.OK);
                // return "redirect:/trabalhos"; // Redireciona para a página de trabalhos após
                // a exclusão
            } else {
                return new ResponseEntity<>("Trabalho não encontrado", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>("Não autenticado", HttpStatus.UNAUTHORIZED); // Redireciona para uma página de
                                                                                 // acesso negado em caso de falha na
                                                                                 // exclusão
    }

}
