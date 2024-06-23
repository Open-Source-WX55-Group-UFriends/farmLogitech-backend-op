package com.farmlogitech.farmlogitechbackend.profiles.infrastructure.persistence.jpa.repositories;

import com.farmlogitech.farmlogitechbackend.profiles.domain.model.aggregates.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findById(Long id);
    List<Employee> findAllByFarmId(Long farmId);

    @Query("SELECT e.id FROM Employee e WHERE e.username = :username")

    Optional<Long> findCollaboratorIdByUsername(@Param("username") String username);

    @Query("SELECT e.farmId FROM Employee e WHERE e.username = :username")
    Optional<Long> findFarmIdByUsername(@Param("username") String username);

    List<Employee> findByNameContainingOrUsernameContainingAndFarmId(String name, String username, Long farmId);

    Employee findByUsername(String username);
}
