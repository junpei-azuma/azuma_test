package org.example.test.task.usecase;

import org.example.test.shared.domain.ID;
import org.example.test.task.domain.Task;
import org.example.test.shared.exception.ResourceNotFoundException;
import org.example.test.task.domain.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetTaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private Task task;

    private GetTaskService getTaskService;

    @BeforeEach
    void setUp() {
        getTaskService = new GetTaskService(taskRepository);
    }

    // 正常系テスト
    @Test
    @DisplayName("存在するIDのタスクを正常に取得する")
    public void testGetTaskByExistingId() {
        // Given
        String taskId = UUID.randomUUID().toString();
        when(taskRepository.findById(any(ID.class))).thenReturn(Optional.of(task));

        // When
        Task result = getTaskService.getTask(taskId);

        // Then
        assertNotNull(result);
        assertEquals(task, result);
    }

    @Test
    @DisplayName("存在しないIDのタスクを取得しようとする")
    public void testGetTaskByNonExistentId() {
        // Given
        String taskId = UUID.randomUUID().toString();
        when(taskRepository.findById(any(ID.class))).thenReturn(Optional.empty());

        // When & Then
        assertThrows(ResourceNotFoundException.class, () -> {
            getTaskService.getTask(taskId);
        });
    }

    @Test
    @DisplayName("不正な形式のIDでタスクを取得しようとする")
    public void testGetTaskByInvalidIdFormat() {
        // Given
        String invalidTaskId = "invalid-uuid-format";

        // When & Then
        assertThrows(ResourceNotFoundException.class, () -> {
            getTaskService.getTask(invalidTaskId);
        });
    }
}
