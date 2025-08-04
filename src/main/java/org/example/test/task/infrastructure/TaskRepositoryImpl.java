package org.example.test.task.infrastructure;

import org.example.test.task.domain.Task;
import org.example.test.shared.domain.ID;
import org.example.test.task.domain.TaskStatus;
import org.example.test.task.domain.TaskRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * タスクリポジトリの実装
 */
@Repository
public class TaskRepositoryImpl implements TaskRepository {

    private final JdbcTemplate jdbcTemplate;

    public TaskRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Task save(Task task) {
        String sql = """
            INSERT INTO task (id, title, complete_condition, start_date, due_date, status, is_postponed)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;
        jdbcTemplate.update(sql,
            task.getId().toString(),
            task.getTitle(),
            task.getCompleteCondition(),
            task.getStartDate(),
            task.getDueDate(),
            task.getStatus().getValue(),
            task.isPostponed()
        );

        return task;
    }

    @Override
    public List<Task> findAll() {
        String sql = """
            SELECT id, title, complete_condition, start_date, due_date, status, is_postponed
            FROM task
            ORDER BY start_date
            """;

        return jdbcTemplate.query(sql, new TaskRowMapper());
    }
    
    @Override
    public Optional<Task> findById(ID id) {
        String sql = """
            SELECT id, title, complete_condition, start_date, due_date, status, is_postponed
            FROM task
            WHERE id = ?
            """;

        List<Task> tasks = jdbcTemplate.query(sql, new TaskRowMapper(), id.toString());
        return tasks.isEmpty() ? Optional.empty() : Optional.of(tasks.get(0));
    }

    /**
     * タスクのRowMapper
     */
    private static class TaskRowMapper implements RowMapper<Task> {
        @Override
        public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Task(
                ID.of(UUID.fromString(rs.getString("id"))),
                rs.getString("title"),
                rs.getString("complete_condition"),
                rs.getDate("start_date").toLocalDate(),
                rs.getDate("due_date").toLocalDate(),
                TaskStatus.values()[rs.getInt("status")],
                rs.getBoolean("is_postponed")
            );
        }
    }
}
