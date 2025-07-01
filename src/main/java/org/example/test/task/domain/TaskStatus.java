package org.example.test.task.domain;

/**
 * タスクのステータスを表すenum
 */
public enum TaskStatus {
    NOT_STARTED("未着手"),
    IN_PROGRESS("進行中"),
    COMPLETED("完了");

    private final String displayName;

    TaskStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
