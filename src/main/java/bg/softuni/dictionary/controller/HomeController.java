package bg.softuni.dictionary.controller;

import bg.softuni.dictionary.config.UserSession;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final UserSession userSession;


    public HomeController(UserSession userSession) {
        this.userSession = userSession;

    }

    @GetMapping("/")
    public String nonLoggedIndex() {

        if (userSession.isLoggedIn()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    @Transactional
    public String loggedInIndex(Model model) {

        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }

        return "home";
    }
}
