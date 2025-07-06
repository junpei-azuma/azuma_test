package org.example.test.task.presentation;

import org.example.test.task.domain.Task;
import org.example.test.task.TaskCreationRequest;
import org.example.test.task.usecase.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    private final TaskService taskService;


    /**
     * コンストラクタ
     *
     * @param taskService タスクサービス
     */
    @Autowired
    public CreateTaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * タスクを作成
     * POST /tasks
     * 入力値を受け取ってタスクを作成し、tasks.htmlを返す
     *
     * @param form タスク作成フォーム
     * @param redirectAttributes リダイレクト属性
     * @return リダイレクト先（tasks.html）
     */
    @PostMapping
    public String createTask(@ModelAttribute TaskForm form,
                             RedirectAttributes redirectAttributes,
                             Model model) {
        // リクエスト内容をログに記録
        logger.info("タスク作成リクエスト受信 - title: {}, completeCondition: {}, startDate: {}, dueDate: {}",
                   form.getTitle(), form.getCompleteCondition(), form.getStartDate(), form.getDueDate());

        try {
            TaskCreationRequest request = new TaskCreationRequest(
                form.getTitle(),
                form.getCompleteCondition(),
                form.getStartDate(),
                form.getDueDate()
            );

            Task createdTask = taskService.createTask(request);

            // タスク作成成功をログに記録
            logger.info("タスク作成成功 - taskId: {}, title: {}",
                       createdTask.getId(), createdTask.getTitle());

            // 画面にセットするためのタスク一覧を取得する
            ArrayList<Task> tasks = taskService.getAllTasks();
            model.addAttribute("tasks", tasks);
            redirectAttributes.addFlashAttribute("successMessage",
                "タスク「" + createdTask.getTitle() + "」を作成しました");
        } catch (IllegalArgumentException e) {
            // エラー情報をログに記録
            logger.error("タスク作成失敗 - エラーメッセージ: {}, リクエスト: title={}, completeCondition={}, startDate={}, dueDate={}",
                        e.getMessage(), form.getTitle(), form.getCompleteCondition(),
                        form.getStartDate(), form.getDueDate(), e);
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }

        // 設計ドキュメント通り、tasks.htmlを返す
        return "redirect:/tasks";
    }
}
