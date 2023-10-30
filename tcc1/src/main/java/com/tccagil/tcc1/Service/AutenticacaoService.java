package com.tccagil.tcc1.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tccagil.tcc1.domain.login.UsuarioDao;
import com.tccagil.tcc1.domain.trabalhos.TrabalhosDao;
import com.tccagil.tcc1.domain.trabalhos.TrabalhosInfo;
import com.tccagil.tcc1.domain.trabalhos.TrabalhosRepository;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;

@Service
public class AutenticacaoService {

    @Autowired
    private TrabalhosRepository trabalhosRepository;

    public boolean isUsuarioLogado(HttpSession session) {
        return session.getAttribute("usuarioLogado") != null;
    }

    public void adicionarInformacoesComuns(Model model, HttpSession session) {

        UsuarioDao usuarioLogado = (UsuarioDao) session.getAttribute("usuarioLogado");
        String nomeUsuario = usuarioLogado.getNome(); 
        model.addAttribute("nomeUsuario", nomeUsuario);

        int idUsuario = (int) session.getAttribute("idUsuarioLogado");

        // List<String> nomesTrabalhos =
        // trabalhosRepository.obterNomesTrabalhosPorUsuario(idUsuario);
        // model.addAttribute("nomesTrabalhos", nomesTrabalhos);
        List<TrabalhosInfo> trabalhos = new ArrayList<>();
        List<TrabalhosDao> trabalhosDoUsuario = trabalhosRepository.obterTrabalhosPorUsuario(idUsuario);
        for (TrabalhosDao trabalho : trabalhosDoUsuario) {
            TrabalhosInfo trabalhoInfo = new TrabalhosInfo(trabalho.getidTrab(), trabalho.getTitulo());
            trabalhos.add(trabalhoInfo);
        }

        model.addAttribute("trabalhos", trabalhos);
    }
}
