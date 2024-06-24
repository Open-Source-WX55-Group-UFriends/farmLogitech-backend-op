package com.farmlogitech.farmlogitechbackend.repository;

import com.farmlogitech.farmlogitechbackend.tasks.domain.model.aggregates.Task;
import com.farmlogitech.farmlogitechbackend.tasks.infrastructure.persistance.jpa.repositories.TaskRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TaskRepositoryTests {
    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void Task_SaveAll_ReturnSavedTask(){
        //Arrange
        Task task = Task.builder().description("asdasdasd").status("asdasd").timeTask(1).
                collaboratorId(1L).farmerId(1L).endDate(null).build();

        //Act
        Task savedTask = taskRepository.save(task);

        //Assert
        Assertions.assertThat(savedTask).isNotNull();
        Assertions.assertThat(savedTask.getCollaboratorId()).isNotNull();

    }
}
