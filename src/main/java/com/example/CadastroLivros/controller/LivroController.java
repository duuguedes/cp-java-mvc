package com.example.CadastroLivros.controller;

import com.example.CadastroLivros.model.Livro;
import com.example.CadastroLivros.repository.LivroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("livro")
public class LivroController {
    @Autowired
    private LivroRepository livroRepository;


    @GetMapping("listar")
    public String listar(Model model) {
        model.addAttribute("livros", livroRepository.findAll());
        return "livro/listar";
    }


    @GetMapping("detalhar/{id}")
    public String detalhar(@PathVariable Long id, Model model) {
        Optional<Livro> optional= livroRepository.findById(id);
        if (optional.isPresent()) {
            model.addAttribute("livro", optional.get());
        } else {
            model.addAttribute("err", "o Livro não existe");
            return "err";
        }
        return "livro/detalhar";
    }


    @GetMapping("cadastrar")
    public String cadastrar(Livro livro, Model model){
        return "livro/cadastrar";
    }


    @PostMapping("cadastrar")
    @Transactional
    public String cadastrar(@Valid Livro livro, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "livro/cadastrar";
        }
        livroRepository.save(livro);
        redirectAttributes.addFlashAttribute("mensagem", "livro cadastrado!");
        return "redirect:/livro/cadastrar";
    }



    @PostMapping("remover")
    @Transactional
    public String remover(Long id, RedirectAttributes redirectAttributes) {
        livroRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("livro", "livro removido!");
        return "redirect:/livro/listar";
    }


    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("livro", livroRepository.findById(id));
        return "livro/editar";
    }


    @PostMapping("editar")
    public String editar(@Valid Livro livro, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "livro/editar";
        }
        livroRepository.save(livro);
        redirectAttributes.addFlashAttribute("livro", "livro atualizado!");
        return "redirect:/livro/listar";
    }

    @GetMapping("pesquisar")
    public String pesquisar(@RequestParam String titulo, Model model) {
        List<Livro> livros = livroRepository.findByTituloContainingIgnoreCase(titulo);
        model.addAttribute("livros", livros);
        return "livro/pesquisar";
    }

}
