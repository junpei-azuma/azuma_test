package org.example.test.shared.domain;

import java.util.UUID;

/**
 * 汎用的なID値オブジェクト
 */
public class ID {
    private final UUID value;

    private ID(UUID value) {
        this.value = value;
    }

    /**
     * 新しいIDを生成する
     */
    public static ID generate() {
        return new ID(UUID.randomUUID());
    }

    /**
     * 既存のUUIDからIDを作成する
     */
    public static ID of(UUID value) {
        return new ID(value);
    }

    /**
     * UUIDを取得する
     */
    public UUID getValue() {
        return value;
    }

    /**
     * 文字列表現を取得する
     */
    public String toString() {
        return value.toString();
    }
}
