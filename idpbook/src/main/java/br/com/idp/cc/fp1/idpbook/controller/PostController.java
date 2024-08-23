package br.com.idp.cc.fp1.idpbook.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.idp.cc.fp1.idpbook.model.Post;
import br.com.idp.cc.fp1.idpbook.model.User;
import br.com.idp.cc.fp1.idpbook.service.PostService;
import br.com.idp.cc.fp1.idpbook.service.UserService;

@Controller
public class PostController {

    private final PostService postService;
    private final UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping({"/", "/posts"})
    public String showPosts(Model model) {
        model.addAttribute("posts", postService.findAllPosts());
        return "posts"; 
    }

    @GetMapping("/posts/add")
    public String showAddPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "add-post";
    }

    @PostMapping("/posts/add")
    public String addPost(@ModelAttribute Post post, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        postService.savePost(post, user);
        return "redirect:/posts"; 
    }

}
