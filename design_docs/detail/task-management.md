このドキュメントではタスク管理の詳細について記述する。

### タスク作成

#### IN/OUT

- `POST /tasks`
- 入力値
  - title
  - compolete_condition (optional)
  - start_date
  - due_date
- 出力値
  - `tasks.html` (タスクの一覧ページ)

#### 処理内容

タスクは、以下の情報を含むオブジェクトとして作成される。
- `id`: タスクの一意な識別子
- `title`: タスクのタイトル
- `complete_condition`: タスクの完了条件(任意)
- `start_date`: タスクの開始日
- `due_date`: タスクの期限
- `status`: タスクのステータス. 未着手で作成される
- `is_postponed`: タスクが延期されているかどうか. デフォルトは`false`

タスクを作成する際は、以下の制約がある。

- titleは必須であり、空文字列は許可されない。
- start_dateは必須であり、未来の日付でなければならない。
- due_dateは必須であり、start_date以降の日付でなければならない。
- complete_conditionは任意であり、空文字列は許可される。

タスクを作成すると、`tasks.html`が返され、タスクの一覧が表示される。

