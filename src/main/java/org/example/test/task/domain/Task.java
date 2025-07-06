package org.example.test.task.domain;

import org.example.test.shared.domain.ID;

import java.time.LocalDate;

/**
 * タスクエンティティクラス
 */
public class Task {
    private final ID id;
    private final String title;
    private final String completeCondition;
    private final LocalDate startDate;
    private final LocalDate dueDate;
    private final TaskStatus status;
    private final boolean isPostponed;

    /**
     * コンストラクタ（新規作成用）
     */
    public Task(ID id, String title, String completeCondition, LocalDate startDate, LocalDate dueDate) {
        this.id = id;
        this.title = title;
        this.completeCondition = completeCondition;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.status = TaskStatus.NOT_STARTED;
        this.isPostponed = false;
    }

    /**
     * コンストラクタ（全項目指定用）
     */
    public Task(ID id, String title, String completeCondition, LocalDate startDate, LocalDate dueDate, TaskStatus status, boolean isPostponed) {
        this.id = id;
        this.title = title;
        this.completeCondition = completeCondition;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.status = status;
        this.isPostponed = isPostponed;
    }

    /**
     * ファクトリメソッド - 新規タスク作成用（IDは自動生成）
     */
    public static Task createNewTask(String title, String completeCondition, LocalDate startDate, LocalDate dueDate) {
        return new Task(ID.generate(), title, completeCondition, startDate, dueDate);
    }

    // Getters only
    public ID getId() {
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
