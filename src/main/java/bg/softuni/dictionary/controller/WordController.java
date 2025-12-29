package bg.softuni.dictionary.controller;

import bg.softuni.dictionary.config.UserSession;
import bg.softuni.dictionary.dto.AddWordDto;
import bg.softuni.dictionary.service.WordService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WordController {

    private final UserSession userSession;
    private final WordService wordService;

    public WordController(UserSession userSession, WordService wordService) {
        this.userSession = userSession;
        this.wordService = wordService;
    }

    @ModelAttribute("wordData")
    public AddWordDto WordData() {
        return new AddWordDto();
    }

    @GetMapping("/word-add")
    public String addWord() {
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }
        return "word-add";
    }

    @PostMapping("/word-add")
    public String doAddWord(@Valid AddWordDto data,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes
    ) {

        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("wordData", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.wordData", bindingResult);

            return "redirect:/word-add";
        }

        boolean success = wordService.create(data);

        if (!success) {
            redirectAttributes.addFlashAttribute("wordData", data);

            return "redirect:/word-add";
        }
        return "redirect:/home";
    }
}
