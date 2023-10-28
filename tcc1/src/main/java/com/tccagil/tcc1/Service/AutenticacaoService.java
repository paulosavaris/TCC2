package com.tccagil.tcc1.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tccagil.tcc1.domain.login.UsuarioDao;
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
        String nomeUsuario = usuarioLogado.getNome(); // Substitua "getNome()" pelo método correto para obter o nome do usuário
        model.addAttribute("nomeUsuario", nomeUsuario);
        
        int idUsuario = (int) session.getAttribute("idUsuarioLogado");


        List<String> nomesTrabalhos = trabalhosRepository.obterNomesTrabalhosPorUsuario(idUsuario);
        model.addAttribute("nomesTrabalhos", nomesTrabalhos);
    }
}
