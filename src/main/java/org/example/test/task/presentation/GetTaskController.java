package org.example.test.task.presentation;

import org.example.test.model.GetTaskResponse;
import org.example.test.task.domain.Task;
import org.example.test.task.usecase.GetTaskService;
import org.springframework.web.bind.annotation.*;

/**
 * タスク取得コントローラー
 * タスクの取得処理を担当する
 */
@RestController
public class GetTaskController {
  private final GetTaskService getTaskService;

  /**
   * コンストラクタ
   *
   * @param getTaskService タスク取得サービス
   */
  public GetTaskController(GetTaskService getTaskService) {
    this.getTaskService = getTaskService;
  }

  /**
   * IDでタスクを取得する
   *
   * @param taskId タスクID
   * @return タスク情報
   */
  @GetMapping("/tasks/{taskId}")
  public GetTaskResponse getTask(@PathVariable String taskId) {
    Task task = getTaskService.getTask(taskId);

    return new GetTaskResponse()
        .id(task.getId().toString())
        .title(task.getTitle())
        .completeCondition(task.getCompleteCondition())
        .startDate(task.getStartDate())
        .dueDate(task.getDueDate())
        .status(task.getStatus().name())
        .isPostponed(task.isPostponed());
  }
}
