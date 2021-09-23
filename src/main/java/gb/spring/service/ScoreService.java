package gb.spring.service;

import gb.spring.entity.User;
import gb.spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScoreService {

     private  final UserRepository userRepository;

     public int increment(String username){
         return  userRepository.incrementScore(username);
     }

     public int decrement(String username){
         return userRepository.decrementScore(username);
     }

    public int currentScore(String username) {
        User user = userRepository.findByUserName(username).orElseThrow( () -> new UsernameNotFoundException(String.format("User %s not found", username)));

        return user.getScore();
    }

    public String userInfo(Long id) {
        User user = userRepository.findById(id).orElseThrow( () -> new UsernameNotFoundException(String.format("User id= %s not found", id)));
        user.setPassword("*********");
        return  user.toString();

    }

}
