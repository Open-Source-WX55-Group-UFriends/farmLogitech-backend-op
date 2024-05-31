package com.farmlogitech.farmlogitechbackend.profiles_managment.infrastructure.persistence.jpa;

import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}
