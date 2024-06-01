package com.farmlogitech.farmlogitechbackend.profiles_managment.infrastructure.persistence.jpa;

import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.aggregates.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
