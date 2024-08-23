package br.com.idp.cc.fp1.idpbook.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.idp.cc.fp1.idpbook.model.Material;
import br.com.idp.cc.fp1.idpbook.model.User;
import br.com.idp.cc.fp1.idpbook.service.MaterialService;
import br.com.idp.cc.fp1.idpbook.service.UserService;

@Controller
public class MaterialController {

    private final MaterialService materialService;
    private final UserService userService;

    // Construtor para injeção de dependências
    public MaterialController(MaterialService materialService, UserService userService) {
        this.userService = userService;
        this.materialService = materialService;
    }

    @GetMapping("/materials")
    public String showMaterials(Model model) {
        model.addAttribute("materials", materialService.findAllMaterials());
        return "materials"; // Nome do template que exibe os materiais
    }

    @GetMapping("/materials/add")
    public String showAddMaterialForm(Model model) {
        model.addAttribute("material", new Material());
        return "add-material"; // Ensure you create this view (add-material.html)
    }

    @PostMapping("/materials/add")
    public String addMaterial(@ModelAttribute Material material, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        materialService.saveMaterial(material, user);
        return "redirect:/materials"; // Redirect to the materials page after adding
    }

}
