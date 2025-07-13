package org.example.test.task.usecase;

import org.example.test.task.TaskCreationRequest;
import org.example.test.task.domain.Task;
import org.example.test.task.domain.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CreateTaskServiceTest {

    @Test
    void createTask_validRequest() {
        var taskRepository = Mockito.mock(TaskRepository.class);
        var request = new TaskCreationRequest("Task 1", "Complete condition 1",
            LocalDate.now().plusDays(1), LocalDate.now().plusDays(2));
        var expectedTask = Task.createNewTask("Task 1", "Complete condition 1",
            LocalDate.now().plusDays(1), LocalDate.now().plusDays(2));

        when(taskRepository.save(any(Task.class))).thenReturn(expectedTask);

        var createTaskService = new CreateTaskService(taskRepository);
        Task result = createTaskService.createTask(request);

        assertNotNull(result);
        assertEquals("Task 1", result.getTitle());
        assertEquals("Complete condition 1", result.getCompleteCondition());
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void createTask_withSameDates() {
        var taskRepository = Mockito.mock(TaskRepository.class);
        var sameDate = LocalDate.now().plusDays(1);
        var request = new TaskCreationRequest("Task 1", "Complete condition",
            sameDate, sameDate);
        var expectedTask = Task.createNewTask("Task 1", "Complete condition",
            sameDate, sameDate);

        when(taskRepository.save(any(Task.class))).thenReturn(expectedTask);

        var createTaskService = new CreateTaskService(taskRepository);
        Task result = createTaskService.createTask(request);

        assertNotNull(result);
        assertEquals(sameDate, result.getStartDate());
        assertEquals(sameDate, result.getDueDate());
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void createTask_repositorySaveIsCalled() {
        var taskRepository = Mockito.mock(TaskRepository.class);
        var request = new TaskCreationRequest("Task 1", "Complete condition",
            LocalDate.now().plusDays(1), LocalDate.now().plusDays(2));
        var expectedTask = Task.createNewTask("Task 1", "Complete condition",
            LocalDate.now().plusDays(1), LocalDate.now().plusDays(2));

        when(taskRepository.save(any(Task.class))).thenReturn(expectedTask);

        var createTaskService = new CreateTaskService(taskRepository);
        createTaskService.createTask(request);

        verify(taskRepository, times(1)).save(any(Task.class));
    }
}
