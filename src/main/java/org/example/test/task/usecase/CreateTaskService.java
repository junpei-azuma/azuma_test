package org.example.test.task.usecase;

import org.example.test.task.TaskCreationRequest;
import org.example.test.task.domain.Task;
import org.example.test.task.domain.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateTaskService {

  private final TaskRepository taskRepository;

  public CreateTaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }
  /**
   * タスクを作成する
   *
   * @param request タスク作成リクエスト
   * @return 作成されたタスク
   */
  public Task createTask(TaskCreationRequest request) {
    Task task = Task.createNewTask(
        request.getTitle(),
        request.getCompleteCondition(),
        request.getStartDate(),
        request.getDueDate()
    );

    return taskRepository.save(task);
  }
}
