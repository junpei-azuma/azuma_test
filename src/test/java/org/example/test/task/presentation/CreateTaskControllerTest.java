package org.example.test.task.presentation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CreateTaskControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("タスク作成の正常系テスト")
  void testCreateTask() throws Exception {
    var startDate = LocalDate.now().plusDays(1);
    var dueDate = LocalDate.now().plusDays(30);
    var requestBody = """
        {
          "title": "Test Task",
          "completeCondition": "test condition",
          "startDate": "%s",
          "dueDate": "%s"
        }
        """.formatted(startDate, dueDate);
    mockMvc.perform(post("/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").isString())
        .andExpect(jsonPath("$.title").value("Test Task"))
        .andExpect(jsonPath("$.completeCondition").value("test condition"))
        .andExpect(jsonPath("$.startDate").value(startDate.toString()))
        .andExpect(jsonPath("$.dueDate").value(dueDate.toString()))
        .andExpect(jsonPath("$.status").value("NOT_STARTED"))
        .andExpect(jsonPath("$.isPostponed").value(false));
  }

  @Test
  @DisplayName("入力値エラーのテスト")
  void testCreateTaskWithInvalidInput() throws Exception {
    var startDate = LocalDate.now().plusDays(1);
    var dueDate = LocalDate.now().plusDays(30);
    var inValidCompleteCondition = "a".repeat(501);
    var requestBody = """
        {
          "title": "",
          "completeCondition": "%s",
          "startDate": "%s",
          "dueDate": "%s"
        }
        """.formatted(inValidCompleteCondition, startDate, dueDate);
    mockMvc.perform(post("/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.status").value(400))
        .andExpect(jsonPath("$.message").value("入力値に不正があります"))
        .andExpect(jsonPath("$.errors.title").value("必須項目が未入力です。"));
  }
}
