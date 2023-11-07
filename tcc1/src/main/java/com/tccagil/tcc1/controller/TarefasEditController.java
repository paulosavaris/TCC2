package com.tccagil.tcc1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tccagil.tcc1.Service.AutenticacaoService;
import com.tccagil.tcc1.domain.membros.MembrosDao;
import com.tccagil.tcc1.domain.membros.MembrosService;
import com.tccagil.tcc1.domain.tarefas.AtivadeRepository;
import com.tccagil.tcc1.domain.tarefas.AtividadeDTO;
import com.tccagil.tcc1.domain.tarefas.AtividadeDao;
import com.tccagil.tcc1.domain.tarefas.AtividadesRecord;
import com.tccagil.tcc1.domain.tarefas.TarefasDTO;
import com.tccagil.tcc1.domain.tarefas.TarefasDao;
import com.tccagil.tcc1.domain.tarefas.TarefasRepository;
import com.tccagil.tcc1.domain.trabalhos.TrabalhosDao;
import com.tccagil.tcc1.domain.trabalhos.TrabalhosService;

import jakarta.servlet.http.HttpSession;

@Controller
public class TarefasEditController {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private TarefasRepository tarefasRepository;

    @Autowired
    private AtivadeRepository ativadeRepository;

    @Autowired
    private TrabalhosService trabalhosIndService;

    @Autowired
    private MembrosService membrosService;

    @GetMapping("/tarefasEdit/{idtarefas}")
    public String exibirTarefa(@PathVariable Long idtarefas, HttpSession session, Model model) {
        if (autenticacaoService.isUsuarioLogado(session)) {
            autenticacaoService.adicionarInformacoesComuns(model, session);

            int idUsuario = (int) session.getAttribute("idUsuarioLogado");

            TarefasDao tarefa = tarefasRepository.findById(idtarefas).orElse(null);

            TrabalhosDao trabalhoIndi = trabalhosIndService.obterTrabalhoPorId(tarefa.getTrabalhoid());
            MembrosDao membrosInd = membrosService.obterMembroIDporTrabalhoLong(trabalhoIndi.getidTrab(), idUsuario);
            model.addAttribute("tarefa", tarefa);

            model.addAttribute("idtarefas", idtarefas);
            if (trabalhoIndi != null && (trabalhoIndi.getIdUsuario() == idUsuario
                    || (membrosInd != null && membrosInd.getUsuarioId() == idUsuario))) {
                List<Object[]> atividades = ativadeRepository.obterAtividadePorTarefa(idtarefas);
                model.addAttribute("atividades", atividades);

                return "tarefasEdit";
            } else {
                // Trabalho não pertence ao usuário logado, você pode redirecioná-lo para uma
                // página de erro ou fazer outra ação
                return "redirect:/Acesso_negado";
            }
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/tarefasEdit/{idtarefas}")
    public String atualizarTarefa(@PathVariable Long idtarefas, AtividadeDTO dados, TarefasDTO form,
            HttpSession session, RedirectAttributes redirectAttributes) {

        String descricaoAtividade = dados.getDescricaoAtividade();
        int usuarioIdAtividade = (int) session.getAttribute("idUsuarioLogado");
        TarefasDao tarefa = tarefasRepository.findById(idtarefas).orElse(null);

        if (descricaoAtividade != null && !descricaoAtividade.isEmpty()) {
            AtividadesRecord novaAtividade = new AtividadesRecord(dados.getDescricaoAtividade(), idtarefas,
                    usuarioIdAtividade);

            AtividadeDao atividade = new AtividadeDao(novaAtividade);
            ativadeRepository.save(atividade);
            // Adicione uma mensagem de sucesso
            redirectAttributes.addFlashAttribute("sucesso", "Atividade cadastrada com sucesso!");
        }

        if (tarefa != null && !tarefa.isEqual(form)) { // Implemente o método isEqual() na classe TarefasDao
            // // Atualize a entidade apenas se os campos forem diferentes
            tarefa.setTitulo(form.getTarefaNomeEdit());
            tarefa.setDescricao(form.getTarefaDescricaoEdit());
            tarefa.setPrioridade(form.getTarefaPrioridadeEdit());
            tarefa.setStatus(form.getStatusTarefasEdit());

            // Atualize a tarefa no banco de dados
            tarefasRepository.save(tarefa);
            // Adicione uma mensagem de sucesso
            redirectAttributes.addFlashAttribute("sucesso", "Atividade cadastrada com sucesso!");
        }
        return "redirect:/tarefasEdit/{idtarefas}";
    }

}
