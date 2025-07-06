package org.example.test.task.domain;

import org.example.test.shared.domain.ID;

import java.util.List;

/**
 * タスクリポジトリインターフェース
 */
public interface TaskRepository {
    /**
     * タスクを保存する
     *
     * @param task 保存するタスク
     * @return 保存されたタスク
     */
    Task save(Task task);

    /**
     * 全てのタスクを取得する
     *
     * @return タスクリスト
     */
    List<Task> findAll();

    /**
     * IDでタスクを検索する
     *
     * @param id タスクID
     * @return タスク（見つからない場合はnull）
     */
    Task findById(ID id);
}
