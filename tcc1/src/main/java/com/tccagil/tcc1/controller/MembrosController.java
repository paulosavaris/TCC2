package com.tccagil.tcc1.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tccagil.tcc1.Service.AutenticacaoService;
import com.tccagil.tcc1.domain.login.UsuarioDao;
import com.tccagil.tcc1.domain.login.UsuarioRepository;
import com.tccagil.tcc1.domain.membros.MembrosDao;
import com.tccagil.tcc1.domain.membros.MembrosRepository;
import com.tccagil.tcc1.domain.membros.MembrosService;
import com.tccagil.tcc1.domain.trabalhos.TrabalhosDao;
import com.tccagil.tcc1.domain.trabalhos.TrabalhosService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MembrosController {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private TrabalhosService trabalhosIndService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MembrosRepository membrosRepository;

    @Autowired
    private MembrosService membrosService;

    @GetMapping("/trabalhoIndi/{idtrab}/Membros")
    public String membros(@PathVariable Long idtrab, HttpSession session, Model model) {
        if (autenticacaoService.isUsuarioLogado(session)) {
            autenticacaoService.adicionarInformacoesComuns(model, session);

            int idUsuario = (int) session.getAttribute("idUsuarioLogado");
            TrabalhosDao trabalhoIndi = trabalhosIndService.obterTrabalhoPorId(idtrab);
            MembrosDao membrosInd = membrosService.obterMembroIDporTrabalho(idtrab, idUsuario);


            List<Object[]> membros = membrosRepository.obterMembroPorTrabalho(idtrab);

            model.addAttribute("trabalhoIndi", trabalhoIndi);
            model.addAttribute("idtrab", idtrab);
            model.addAttribute("membros", membros);

            // Verifica se o trabalho existe e se o usuário logado é o proprietário do
            // trabalho
            if (trabalhoIndi != null && (trabalhoIndi.getIdUsuario() == idUsuario
            || (membrosInd != null && membrosInd.getUsuarioId() == idUsuario))) {
                model.addAttribute("trabalhoIndi", trabalhoIndi);

                return "Membros"; // Página para criar uma nova tarefa
            } else {
                return "redirect:/Acesso_negado";
            }
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/trabalhoIndi/{idtrab}/Membros")
    public String adicionarMembroAoTrabalho(@PathVariable Long idtrab, @RequestParam("email") String email,
            HttpSession session, Model model) {
        // Consulte o banco de dados para encontrar o usuário com o e-mail fornecido
        int idUsuarioLogado = (int) session.getAttribute("idUsuarioLogado");

        UsuarioDao usuario = usuarioRepository.findByEmail(email);

        if (usuario != null) {
            TrabalhosDao trabalho = trabalhosIndService.obterTrabalhoPorId(idtrab);
            MembrosDao membrosInd = membrosService.obterMembroIDporTrabalho(idtrab, idUsuarioLogado);
            model.addAttribute("idtrab", idtrab);
            // Verifique se o usuário logado é o proprietário do trabalho
            if (trabalho != null && (trabalho.getIdUsuario() == idUsuarioLogado
            || (membrosInd != null && membrosInd.getUsuarioId() == idUsuarioLogado)))  {
                // Crie e associe o membro ao trabalho e ao usuário
                MembrosDao membro = new MembrosDao();
                membro.setNome(usuario.getNome());
                membro.setTrabalhoId(idtrab);
                membro.setUsuarioId(usuario.getIdUsuario());

                // Salve o membro no banco de dados
                membrosRepository.save(membro);
            }
        }
        return "redirect:/trabalhoIndi/" + idtrab + "/Membros";
    }

    @GetMapping("/buscarEmails")
    @ResponseBody
    public List<String> buscarEmails(@RequestParam("term") String term) {
        // Consulte o banco de dados para buscar e-mails correspondentes com base no
        // termo de pesquisa
        List<String> emails = usuarioRepository.findEmailsByTerm(term);
        return emails;
    }

    @PostMapping("/trabalhoIndi/{idtrab}/Membros/removeMember/{idMembros}")
    @ResponseBody
    public ResponseEntity<String> removerMembro(
            @PathVariable Long idtrab, @PathVariable Long idMembros, Model model) {
        // Verifique se o membro existe
        Optional<MembrosDao> membroOptional = membrosRepository.findById(idMembros);

            model.addAttribute("idtrab", idtrab);

        if (membroOptional.isPresent()) {
            // Se o membro existe, remova-o do banco de dados
            membrosRepository.delete(membroOptional.get());
            return new ResponseEntity<>("Membro removido com sucesso", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Membro não encontrado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}