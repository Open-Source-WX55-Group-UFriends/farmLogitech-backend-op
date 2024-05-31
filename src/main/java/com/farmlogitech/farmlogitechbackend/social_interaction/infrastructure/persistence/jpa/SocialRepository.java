package com.farmlogitech.farmlogitechbackend.social_interaction.infrastructure.persistence.jpa;

import com.farmlogitech.farmlogitechbackend.social_interaction.domain.model.aggregates.Social;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocialRepository extends JpaRepository<Social, Integer> {

    @Query("SELECT s FROM Social s")
    List<Social> findAllSocial();
}
