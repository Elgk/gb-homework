package gb.spring.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.NamedNativeQueries;

import javax.persistence.*;
import java.util.Collection;


@Entity
@Data
@ToString
@RequiredArgsConstructor
@Table(name = "users")
@NamedNativeQueries(value = {
        @NamedNativeQuery(
                name = "User.findByName",
                query = "select * from users where username = ?1",
                resultClass = User.class),
        @NamedNativeQuery(
                name = "User.incrementScore",
                query = "update users set score = score + 1 where username = ?1 returning score"),
        @NamedNativeQuery(
                name = "User.decrementScore",
                query = "update users set score = score - 1 where username = ?1 returning score"
        )
})
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "username")
        private String username;

        @Column(name = "password")
        private String password;

        @Column(name = "email")
        private String email;

        @Column(name = "score")
        private int score;

        @ManyToMany
        @JoinTable(name = "users_roles",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
        @ToString.Exclude // не включать атрибут  в результат выполнения object.toString, при этом должна быть аннотация @ToString на класс
        private Collection<Role> roles;

        @Override
        public int hashCode() {
                return 562048007;
        }
}
