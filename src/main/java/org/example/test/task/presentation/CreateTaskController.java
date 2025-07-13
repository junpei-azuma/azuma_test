package org.example.test.task.presentation;

import org.example.test.task.domain.Task;
import org.example.test.task.TaskCreationRequest;
import org.example.test.task.usecase.CreateTaskService;
import org.example.test.task.usecase.ReadTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

/**
 * タスク作成コントローラー
 * タスクの作成処理を担当する
 */
@Controller
@RequestMapping("/tasks")
public class CreateTaskController {

  private static final Logger logger = LoggerFactory.getLogger(CreateTaskController.class);

  private final ReadTaskService readTaskService;
  private final CreateTaskService createTaskService;


  /**
   * コンストラクタ
   *
   * @param readTaskService タスクサービス
   */
  @Autowired
  public CreateTaskController(CreateTaskService createTaskService, ReadTaskService readTaskService) {
    this.readTaskService = readTaskService;
    this.createTaskService = createTaskService;
  }

  /**
   * タスクを作成
   * POST /tasks
   * 入力値を受け取ってタスクを作成し、tasks.htmlを返す
   *
   * @param createTaskForm     タスク作成フォーム
   * @param bindingResult      バリデーション結果
   * @param redirectAttributes リダイレクト属性
   * @param model              モデル
   * @return リダイレクト先（tasks.html）
   */
  @PostMapping
  public String createTask(@ModelAttribute @Validated CreateTaskForm createTaskForm,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           Model model) {
    // リクエスト内容をログに記録
    logger.info("タスク作成リクエスト受信 - title: {}, completeCondition: {}, startDate: {}, dueDate: {}",
        createTaskForm.getTitle(), createTaskForm.getCompleteCondition(), createTaskForm.getStartDate(), createTaskForm.getDueDate());

    try {
      if (bindingResult.hasErrors()) {
        // バリデーションエラー時はタスク一覧を再取得してフォームと一緒に表示
        ArrayList<Task> tasks = readTaskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks";
      }
      TaskCreationRequest request = new TaskCreationRequest(
          createTaskForm.getTitle(),
          createTaskForm.getCompleteCondition(),
          createTaskForm.getStartDate(),
          createTaskForm.getDueDate()
      );

      Task createdTask = createTaskService.createTask(request);

      // タスク作成成功をログに記録
      logger.info("タスク作成成功 - taskId: {}, title: {}",
          createdTask.getId(), createdTask.getTitle());

    } catch (IllegalArgumentException e) {
      // エラー情報をログに記録
      logger.error("タスク作成失敗 - エラーメッセージ: {}, リクエスト: title={}, completeCondition={}, startDate={}, dueDate={}",
          e.getMessage(), createTaskForm.getTitle(), createTaskForm.getCompleteCondition(),
          createTaskForm.getStartDate(), createTaskForm.getDueDate(), e);
      redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
    }
    // 設計ドキュメント通り、tasks.htmlを返す
    return "redirect:/tasks";
  }
}
