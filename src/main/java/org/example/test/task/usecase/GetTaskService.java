package org.example.test.task.usecase;

import org.example.test.shared.domain.ID;
import org.example.test.task.domain.Task;
import org.example.test.shared.exception.ResourceNotFoundException;
import org.example.test.task.domain.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * タスク取得サービス
 */
@Service
public class GetTaskService {

    private final TaskRepository taskRepository;

    public GetTaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * IDでタスクを取得する
     *
     * @param taskId タスクID文字列
     * @return 取得されたタスク
     * @throws ResourceNotFoundException タスクが見つからない場合
     */
    public Task getTask(String taskId) {
            UUID uuid = UUID.fromString(taskId);
            ID id = ID.of(uuid);
            return taskRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }
}
