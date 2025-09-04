package com.ig.training.repositories;

import com.ig.training.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    public Optional<Users> findByIdAndStatusTrue(Long id);
    public Optional<Users> findByUsernameIgnoreCaseOrEmailIgnoreCaseAndStatusTrue(String username, String email);
}
