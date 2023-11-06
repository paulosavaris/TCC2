/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tccagil.tcc1.controller;

import com.tccagil.tcc1.Service.AutenticacaoService;
import com.tccagil.tcc1.domain.trabalhos.TrabalhosDao;
import com.tccagil.tcc1.domain.trabalhos.TrabalhosDto;
import com.tccagil.tcc1.domain.trabalhos.TrabalhosRecord;
import com.tccagil.tcc1.domain.trabalhos.TrabalhosRepository;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;

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

    @Autowired
    private AutenticacaoService autenticacaoService;

    @GetMapping("/trabalhos")
    public String newJob(HttpSession session, Model model) {
        if (autenticacaoService.isUsuarioLogado(session)) {
            autenticacaoService.adicionarInformacoesComuns(model, session);
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

            // Validacao de Datas
            LocalDate data_iniTrabalho = dados.getData_iniTrabalho();
            LocalDate prazo_entregaTrabalho = dados.getPrazo_entregaTrabalho();
            LocalDate dataAtual = LocalDate.now();

            if (data_iniTrabalho.isBefore(dataAtual)) {
                model.addAttribute("error", "A data de início não pode ser anterior à data atual.");
                return "trabalhos"; // Substitua pelo nome da sua página de formulário
            }
    
            if (prazo_entregaTrabalho.isBefore(data_iniTrabalho)) {
                model.addAttribute("error", "O prazo de entrega deve ser posterior à data de início.");
                return "trabalhos"; // Substitua pelo nome da sua página de formulário
            }

            TrabalhosRecord newJobRecord = new TrabalhosRecord(idusuario_responsavel, dados.getTituloTrabalho(),
                    dados.getDescricaoTrabalho(), dados.getData_iniTrabalho(), dados.getPrazo_entregaTrabalho());

            TrabalhosDao newJob = new TrabalhosDao(newJobRecord);
            trabalhosRepository.save(newJob);

            return "redirect:/dash_trabalhos"; // Redirecione para a página de dashboard após a criação do trabalho
        } else {
            return "redirect:/login";
        }
    }
}
