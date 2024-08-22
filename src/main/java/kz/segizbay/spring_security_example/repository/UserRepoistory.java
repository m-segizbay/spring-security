package kz.segizbay.spring_security_example.repository;

import kz.segizbay.spring_security_example.models.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepoistory extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findByUsername(String username);
}
