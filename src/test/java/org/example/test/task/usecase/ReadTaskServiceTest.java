package org.example.test.task.usecase;

import org.example.test.task.domain.Task;
import org.example.test.task.domain.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReadTaskServiceTest {

    @Test
    void getAllTask() {
        var taskRepository = Mockito.mock(TaskRepository.class);
        Task task1 = Task.createNewTask("Task 1", "Complete condition 1", LocalDate.now().plusDays(1), LocalDate.now().plusDays(2));
        Task task2 = Task.createNewTask("Task 2", "Complete condition 2", LocalDate.now().plusDays(3), LocalDate.now().plusDays(4));
        List<Task> tasks = List.of(task1, task2);
        when(taskRepository.findAll()).thenReturn(tasks);

        var readTaskService = new ReadTaskService(taskRepository);
        ArrayList<Task> result = readTaskService.getAllTasks();

        assertEquals(2, result.size());
        assertTrue(result.contains(task1));
        assertTrue(result.contains(task2));
    }

    @Test
    void getAllTasks_empty() {
        var taskRepository = Mockito.mock(TaskRepository.class);
        when(taskRepository.findAll()).thenReturn(List.of());
        var readTaskService = new ReadTaskService(taskRepository);
        ArrayList<Task> result = readTaskService.getAllTasks();

        assertTrue(result.isEmpty());
    }
}
