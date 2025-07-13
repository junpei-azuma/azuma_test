package org.example.test.task;

import java.time.LocalDate;

/**
 * タスク作成リクエストDTO
 */
public class TaskCreationRequest {
    private String title;
    private String completeCondition;
    private LocalDate startDate;
    private LocalDate dueDate;

    /**
     * コンストラクタ
     *
     * @param title            タイトル
     * @param completeCondition 完了条件
     * @param startDate        開始日
     * @param dueDate          期限日
     */
    public TaskCreationRequest(String title, String completeCondition, LocalDate startDate, LocalDate dueDate) {
        this.title = title;
        this.completeCondition = completeCondition;
        this.startDate = startDate;
        this.dueDate = dueDate;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompleteCondition() {
        return completeCondition;
    }

    public void setCompleteCondition(String completeCondition) {
        this.completeCondition = completeCondition;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
