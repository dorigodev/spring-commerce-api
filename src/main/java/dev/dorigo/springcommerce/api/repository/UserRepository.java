package dev.dorigo.springcommerce.api.repository;

import dev.dorigo.springcommerce.api.domain.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<UserDetails> findByEmail(String email);
}
