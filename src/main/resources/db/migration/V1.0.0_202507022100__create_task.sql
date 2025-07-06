CREATE TABLE task (
    id VARCHAR(36) NOT NULL, -- UUID形式のID. アプリケーション側で生成する
    name VARCHAR(255) NOT NULL,
    complete_condition TEXT,
    is_postponed BOOLEAN NOT NULL DEFAULT FALSE,
    status integer NOT NULL DEFAULT 0, -- 0: 未着手, 1: 進行中, 2: 完了, 3: 未達成
    start_date DATE NOT NULL,
    due_date DATE NOT NULL,
    complete_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT check_date_order CHECK (start_date <= due_date)
);