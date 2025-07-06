package org.example.test.task.usecase;

import org.example.test.task.domain.Task;
import org.example.test.task.TaskCreationRequest;
import org.example.test.task.domain.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * タスク管理サービス
 */
@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
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

    /**
     * タスクを作成する
     *
     * @param request タスク作成リクエスト
     * @return 作成されたタスク
     * @throws IllegalArgumentException バリデーションエラーの場合
     */
    public Task createTask(TaskCreationRequest request) {
        validateTaskCreationRequest(request);

        Task task = Task.createNewTask(
            request.getTitle(),
            request.getCompleteCondition(),
            request.getStartDate(),
            request.getDueDate()
        );

        return taskRepository.save(task);
    }

    /**
     * タスク作成リクエストのバリデーション
     *
     * @param request タスク作成リクエスト
     * @throws IllegalArgumentException バリデーションエラーの場合
     */
    private void validateTaskCreationRequest(TaskCreationRequest request) {
        if (request.getTitle() == null || request.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("タイトルは必須です");
        }

        if (request.getStartDate() == null) {
            throw new IllegalArgumentException("開始日は必須です");
        }

        if (request.getDueDate() == null) {
            throw new IllegalArgumentException("期限日は必須です");
        }

        if (request.getStartDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("開始日は未来の日付である必要があります");
        }

        if (request.getDueDate().isBefore(request.getStartDate())) {
            throw new IllegalArgumentException("期限日は開始日以降の日付である必要があります");
        }
    }
}
