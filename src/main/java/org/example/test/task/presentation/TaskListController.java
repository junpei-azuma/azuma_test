package org.example.test.task.presentation;

import org.example.test.task.domain.Task;
import org.example.test.task.TaskCreationRequest;
import org.example.test.task.usecase.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * タスク一覧表示コントローラー
 * タスクの一覧表示を担当する
 */
@Controller
@RequestMapping("/tasks")
public class TaskListController {

    private final TaskService taskService;

    /**
     * コンストラクタ
     *
     * @param taskService タスクサービス
     */
    @Autowired
    public TaskListController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * タスク一覧ページを表示
     *
     * @param model モデル
     * @return テンプレート名
     */
    @GetMapping
    public String showTasksPage(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }
}
