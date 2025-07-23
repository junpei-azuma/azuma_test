package org.example.test.task.presentation;

import jakarta.validation.Valid;
import org.example.test.model.CreateTaskRequest;
import org.example.test.model.CreateTaskResponse;
import org.example.test.task.domain.Task;
import org.example.test.task.TaskCreationRequest;
import org.example.test.task.usecase.CreateTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * タスク作成コントローラー
 * タスクの作成処理を担当する
 */
@RestController
public class CreateTaskController {

  private static final Logger logger = LoggerFactory.getLogger(CreateTaskController.class);
  private final CreateTaskService createTaskService;

  /**
   * コンストラクタ
   *
   * @param createTaskService タスク作成サービス
   */
  public CreateTaskController(CreateTaskService createTaskService) {
    this.createTaskService = createTaskService;
  }

  @PostMapping("/tasks")
  public CreateTaskResponse createTask(@Valid @RequestBody CreateTaskRequest createTaskRequest) {
    logger.info("タスク作成リクエスト - title: {}, completeCondition: {}, startDate: {}, dueDate: {}",
        createTaskRequest.getTitle(), createTaskRequest.getCompleteCondition(), createTaskRequest.getStartDate(), createTaskRequest.getDueDate());
    TaskCreationRequest request = new TaskCreationRequest(
        createTaskRequest.getTitle(),
        createTaskRequest.getCompleteCondition(),
        createTaskRequest.getStartDate(),
        createTaskRequest.getDueDate()
    );
    Task createdTask = createTaskService.createTask(request);
    logger.info("task created successfully - id: {}, title: {}",
        createdTask.getId(), createdTask.getTitle());

    return new CreateTaskResponse()
        .id(createdTask.getId().toString())
        .title(createdTask.getTitle())
        .completeCondition(createdTask.getCompleteCondition())
        .startDate(createdTask.getStartDate())
        .dueDate(createdTask.getDueDate())
        .status(createdTask.getStatus().name())
        .isPostponed(createdTask.isPostponed());
  }
}
