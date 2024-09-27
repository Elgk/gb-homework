package gb.spring.repository;

import gb.spring.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername (String username);

    @Transactional
    int incrementScore(String username);

    @Transactional
    int decrementScore(String username);

}
