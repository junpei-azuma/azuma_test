package org.example.test.task.presentation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GetTaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("存在するIDのタスクを正常に取得する")
    @Sql("classpath:org/example/test/task/presentation/insert-test-task.sql")
    public void testGetTaskByExistingId() throws Exception {
        mockMvc.perform(get("/tasks/550e8400-e29b-41d4-a716-446655440000"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value("550e8400-e29b-41d4-a716-446655440000"))
            .andExpect(jsonPath("$.title").value("テストタスク"))
            .andExpect(jsonPath("$.completeCondition").value("テスト完了条件"))
            .andExpect(jsonPath("$.startDate").value("2025-01-01"))
            .andExpect(jsonPath("$.dueDate").value("2025-01-31"))
            .andExpect(jsonPath("$.status").value("NOT_STARTED"))
            .andExpect(jsonPath("$.isPostponed").value(false));
    }

    @Test
    @DisplayName("存在しないIDのタスクを取得しようとする")
    public void testGetTaskByNonExistentId() throws Exception {
        mockMvc.perform(get("/tasks/e3b0c442-98fc-4e9c-bf2f-3c3de191ad8a"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.status").value(404))
            .andExpect(jsonPath("$.message").value("指定したリソースが見つかりません。"));
    }

    @Test
    @DisplayName("不正な形式のIDでタスクを取得しようとする")
    public void testGetTaskByInvalidIdFormat() throws Exception {
        mockMvc.perform(get("/tasks/invalid-id-format"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.status").value(404))
            .andExpect(jsonPath("$.message").value("指定したリソースが見つかりません。"));;
    }
}
