package gb.spring.controller;

import gb.spring.entity.User;
import gb.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/score")
@RequiredArgsConstructor
public class ScoreController {

    private final UserService userService;

   // @PreAuthorize("hasAuthority('UPDATE_SCORE')")
    @GetMapping(value = "/inc")
    public int incScore(Principal principal) {
        return userService.incrementScore(principal.getName());
    }

    @GetMapping(value = "/dec")
    public int decScore(Principal principal) {
        return userService.decrementScore(principal.getName());
    }

    @GetMapping(value = "/get/current")
    public String currentScore(Principal principal) {
        User user = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException(String.format("user with name: %s not found", principal.getName())));

        return String.format("Username: %s, score: %s", user.getUsername(), user.getScore());
    }

    @PreAuthorize("hasRole('ADMIN')")   // @Secured("ROLE_ADMIN")
    @GetMapping(value = "/get/current/{id}")
    public User userInfo(@PathVariable Long id) {
        User user = userService.findById(id).orElseThrow(() -> new RuntimeException(String.format("User with id %d not found", id)));
        user.setPassword("*************");
        return user;
    }
}
