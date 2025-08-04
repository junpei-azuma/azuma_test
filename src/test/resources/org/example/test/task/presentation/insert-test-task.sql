TRUNCATE TABLE task RESTART IDENTITY CASCADE;

INSERT INTO task (
    id,
    title,
    complete_condition,
    is_postponed,
    status,
    start_date,
    due_date,
    complete_date,
    created_at,
    updated_at
) VALUES (
    '550e8400-e29b-41d4-a716-446655440000',
    'テストタスク',
    'テスト完了条件',
    FALSE,
    0,
    '2025-01-01',
    '2025-01-31',
    NULL,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);

