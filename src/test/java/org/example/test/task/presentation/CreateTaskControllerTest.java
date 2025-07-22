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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CreateTaskControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("入力値エラーのテスト")
  void testCreateTaskWithInvalidInput() throws Exception {
    var startDate = LocalDate.now().plusDays(1);
    var dueDate = LocalDate.now().plusDays(30);
    var requestBody = """
        {
          "title": "",
          "completeCondition": "test condition",
          "startDate": "%s",
          "dueDate": "%s"
        }
        """.formatted(startDate, dueDate);
    mockMvc.perform(post("/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json("{\"status\": 400, \"message\": \"入力値に不正があります\", \"errors\": {\"title\": \"必須項目が未入力です。\"}}"));
  }
}
