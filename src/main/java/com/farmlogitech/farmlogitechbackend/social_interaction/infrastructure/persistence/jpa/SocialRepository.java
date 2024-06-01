package com.farmlogitech.farmlogitechbackend.social_interaction.infrastructure.persistence.jpa;

import com.farmlogitech.farmlogitechbackend.social_interaction.domain.model.aggregates.Social;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface SocialRepository extends JpaRepository<Social, Integer> {

    List<Social> findAllByFarmId(Long farmId);
}
