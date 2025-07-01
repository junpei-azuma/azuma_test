package org.example.test.task.usecase;

import org.example.test.task.domain.Task;
import org.example.test.task.TaskCreationRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * タスク管理サービス
 */
@Service
public class TaskService {
    private final List<Task> tasks = new ArrayList<>();

    /**
     * 全てのタスクを取得する
     *
     * @return タスクリスト
     */
    public ArrayList<Task> getAllTasks() {
        var sampleTask = new Task(
           UUID.randomUUID(),  "Sample Task", "Sample Condition", LocalDate.now(), LocalDate.now().plusDays(7)
        );
        return new ArrayList<>(List.of(sampleTask));
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
