package com.farmlogitech.farmlogitechbackend.iam.infrastructure.jpa.repositories;

import com.farmlogitech.farmlogitechbackend.iam.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existByUsername(String username);

}
