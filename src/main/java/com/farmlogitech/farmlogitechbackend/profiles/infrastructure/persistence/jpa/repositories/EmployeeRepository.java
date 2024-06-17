package com.farmlogitech.farmlogitechbackend.profiles.infrastructure.persistence.jpa.repositories;

import com.farmlogitech.farmlogitechbackend.profiles.domain.model.aggregates.Employee;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.aggregates.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findById(Long id);
    List<Employee> findAllByFarmId(Long farmId);


}
