package br.com.idp.cc.fp1.idpbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home"; // nome do template home.html
    }
    
    @GetMapping("/")
    public String index() {
        return "home"; // Redireciona para a página home quando acessar a raiz
    }
}
