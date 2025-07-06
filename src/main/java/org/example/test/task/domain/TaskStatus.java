package org.example.test.task.domain;

/**
 * タスクのステータスを表すenum
 */
public enum TaskStatus {
    NOT_STARTED(0, "未着手"),
    IN_PROGRESS(1, "着手中"),
    COMPLETED(2, "完了"),
    NOT_ACHIEVED(3, "未達成");

    private final int value;
    private final String displayName;

    TaskStatus(int value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }

    public int getValue() {
        return value;
    }

    public String getDisplayName() {
        return displayName;
    }
}
