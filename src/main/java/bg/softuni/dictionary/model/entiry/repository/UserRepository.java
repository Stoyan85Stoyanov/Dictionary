package bg.softuni.dictionary.model.entiry.repository;


import bg.softuni.dictionary.model.entiry.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsernameOrEmail(String username, String email);

    Optional<User> findByUsername(String username);

    Optional<User> findById(Long id);

}
