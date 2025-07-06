CREATE TABLE label (
    id VARCHAR(36) NOT NULL, -- UUID形式のID. アプリケーション側で生成する
    name VARCHAR(255) NOT NULL,
    color VARCHAR(20) DEFAULT '#FFFFFF', -- ラベルの色を表す16進数カラーコード
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);