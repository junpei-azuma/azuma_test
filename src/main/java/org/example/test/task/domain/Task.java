package org.example.test.task.domain;

import java.time.LocalDate;
import java.util.UUID;

/**
 * タスクエンティティクラス
 */
public class Task {
    private final UUID id;
    private final String title;
    private final String completeCondition;
    private final LocalDate startDate;
    private final LocalDate dueDate;
    private final TaskStatus status;
    private final boolean isPostponed;

    /**
     * コンストラクタ
     */
    public Task(UUID id, String title, String completeCondition, LocalDate startDate, LocalDate dueDate) {
        this.id = id ;
        this.title = title;
        this.completeCondition = completeCondition;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.status = TaskStatus.NOT_STARTED;
        this.isPostponed = false;
    }

    /**
     * ファクトリメソッド - 新規タスク作成用
     */
    public static Task createNewTask(UUID id, String title, String completeCondition, LocalDate startDate, LocalDate dueDate) {
        return new Task(id, title, completeCondition, startDate, dueDate);
    }

    // Getters only
    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCompleteCondition() {
        return completeCondition;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public boolean isPostponed() {
        return isPostponed;
    }
}
