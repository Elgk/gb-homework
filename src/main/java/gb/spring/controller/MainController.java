package gb.spring.controller;

import gb.spring.entity.User;
import gb.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final UserService userService;

    @GetMapping
    public String homePage(){
        return "home";
    }

    @GetMapping("/unsecured")
    public String unsecuredPage(){
        return "unsecured";
    }

    @GetMapping("/admin")
    public String adminPage(){
        return "admin";
    }

    @GetMapping("/user_info")
    public String userPage(Principal principal){
        User user = userService.findByUsername(principal.getName()).orElseThrow(() ->
               new UsernameNotFoundException(String.format("User: %s not found", principal.getName())));

        return String.format("User:  username = %s, email = %s, score = %d", user.getUsername(), user.getEmail(), user.getScore());
    }
}
