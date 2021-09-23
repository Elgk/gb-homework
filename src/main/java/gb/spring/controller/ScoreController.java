package gb.spring.controller;

import gb.spring.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/app/score")
@RequiredArgsConstructor
public class ScoreController {
    private final ScoreService scoreService;

    @GetMapping(value = "/inc")
    public int incScore(Principal principal) {
        return scoreService.increment(principal.getName());
    }

    @GetMapping(value = "/dec")
    public int decScore(Principal principal) {
        return scoreService.decrement(principal.getName());
    }

    @GetMapping(value = "/get/current")
    public int currentScore(Principal principal) {
        return scoreService.currentScore(principal.getName());
    }

    @GetMapping(value = "/get/current/{id}")
    public String userInfo(@PathVariable Long id, Principal principal) {
        return scoreService.userInfo(id);
    }
}
