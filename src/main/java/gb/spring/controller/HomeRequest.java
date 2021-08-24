package gb.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeRequest {

    @RequestMapping
    public String homePage(Model model){
        return "home";
    }
}
