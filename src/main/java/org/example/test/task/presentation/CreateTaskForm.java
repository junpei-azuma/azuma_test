package org.example.test.task.presentation;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

/**
 * タスク作成フォームクラス
 */
public class CreateTaskForm {
    @NotBlank
    private String title;
    private String completeCondition;
    @NotNull
    private LocalDate startDate;
    @NotNull
    @FutureOrPresent
    private LocalDate dueDate;

    @AssertTrue(message = "期限日は開始日以降の日付を選択してください")
    public boolean isValidDates() {
        if (startDate == null || dueDate == null) {
            return true; // null の場合は他のバリデーションに任せる
        }
        return !startDate.isAfter(dueDate);
    }

    // デフォルトコンストラクタ
    public CreateTaskForm() {
    }

    // コンストラクタ
    public CreateTaskForm(String title, String completeCondition, LocalDate startDate, LocalDate dueDate) {
        this.title = title;
        this.completeCondition = completeCondition;
        this.startDate = startDate;
        this.dueDate = dueDate;
    }

    // Getter & Setter for title
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
