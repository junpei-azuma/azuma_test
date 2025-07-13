package org.example.test.task.usecase;

import org.example.test.task.domain.Task;
import org.example.test.task.TaskCreationRequest;
import org.example.test.task.domain.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * タスク管理サービス
 */
@Service
public class ReadTaskService {
    private final TaskRepository taskRepository;

    public ReadTaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * 全てのタスクを取得する
     *
     * @return タスクリスト
     */
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(taskRepository.findAll());
    }
}
