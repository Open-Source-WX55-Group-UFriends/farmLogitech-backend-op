package com.farmlogitech.farmlogitechbackend.profiles.infrastructure.persistence.jpa.repositories;


import com.farmlogitech.farmlogitechbackend.profiles.domain.model.aggregates.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
