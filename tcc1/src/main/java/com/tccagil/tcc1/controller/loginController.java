package com.tccagil.tcc1.controller;

import com.tccagil.tcc1.domain.login.UsuarioDao;
import com.tccagil.tcc1.domain.login.UsuarioRecord;
import com.tccagil.tcc1.domain.login.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author paulo
 */
@Controller
@RequestMapping("/login")
public class loginController {
    
        @Autowired
    private UsuarioRepository repository;
    
    @GetMapping
    public String carregaPagLogin() {
        return "login";
    }
    
    @PostMapping(params = "formAction=login")
    public String realizaLogin(@RequestParam("loginEmail") String loginEmail,
            @RequestParam("loginPassword") String loginPassword,
            HttpSession session) {
        UsuarioDao usuario = repository.findByEmailAndSenha(loginEmail, loginPassword);

        if (usuario != null) {
            // Login bem-sucedido, armazene informações do usuário na sessão (ou onde
            // preferir)
            session.setAttribute("usuarioLogado", usuario);
            session.setAttribute("idUsuarioLogado", usuario.getIdUsuario());

            // Redirecione para a página após o login bem-sucedido
            return "redirect:/trabalhos"; // Substitua com o caminho correto da sua página
        } else {
            // Login falhou, você pode retornar uma mensagem de erro
            // ou fazer o tratamento adequado, como redirecionar de volta ao formulário com
            // uma mensagem de erro.
            return "redirect:/login?errorL=Email ou senha incorretos";
        }

    }

    @PostMapping(params = "formAction=cadastra")
    public String cadastraUser(UsuarioRecord dados) {
        // Verificar se a senha e a confirmação de senha são iguais
        if (!dados.CadastroPassword().equals(dados.CadastroConfirmPassword())) {
            // Senha e confirmação de senha não correspondem, você pode retornar uma
            // mensagem de erro
            // ou fazer o tratamento adequado, como redirecionar de volta ao formulário com
            // uma mensagem de erro.
            return "redirect:/login?errorC=Senhas nao correspondem";
        }

        var userCadastro = new UsuarioDao(dados);
        // userCadaTeste.add(userCadastro);
        repository.save(userCadastro);
        System.out.println(repository.findAll());

        return "login";
    }
    
}
