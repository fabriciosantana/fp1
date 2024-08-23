package br.com.idp.cc.fp1.idpbook.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.idp.cc.fp1.idpbook.model.User;
import br.com.idp.cc.fp1.idpbook.service.UserService;
import jakarta.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Nome do template login.html
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login?logout"; // Redireciona para a página de login após o logout
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }

        // Verificar se o nome de usuário já está registrado
        if (userService.findByEmail(user.getEmail()) != null) {
            model.addAttribute("emailError", "Email já existe.");
            return "register";
        }

        // Codificar a senha antes de salvar
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Salvar o usuário no banco de dados
        userService.save(user);

        // Redirecionar para a página de login após o registro bem-sucedido
        return "redirect:/login";
    }

    @PostMapping("/perform_login")
    public String loginUser(@RequestParam("email") String email,
                            @RequestParam("password") String password,
                            Model model) {
        User user = userService.findByEmail(email);

        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            model.addAttribute("loginError", "Email ou senha incorretos.");
            return "redirect:login?error=true";
        }

        // Redirecionar para a página de materiais após login bem-sucedido
        return "redirect:/materials";
    }
}

