---
applyTo: "**"
---

# プロジェクト全体のガイドライン

全てのコードは、以下のガイドラインに従って記述すること。

## アーキテクチャ
> [!NOTE]
>
> 本リポジトリは、シングルプロジェクト構成を採用する。
>  * 理由1. シングルプロジェクト構成は、開発者がプロジェクト全体を把握しやすく、管理が容易であるため。
>  * 理由2. シングルプロジェクト構成は、依存関係の管理が簡単で、ビルドやデプロイのプロセスがシンプルになるため。
>
### ディレクトリ構成例

> [!NOTE]
>
> 本リポジトリはDomainやUsecaseといったレイヤーではなく、機能や概念によりパッケージを分割する。
>  * 理由 レイヤーでパッケージングすると、機能に関するクラスがパッケージを跨いで散在し、見通しが悪くなるため

> 本リポジトリは機能ごとのパッケージの内部ではオニオンアーキテクチャにより、ディレクトリ分割を行う
>  * 理由1.  オニオンアーキテクチャは、依存関係を内側から外側へと明確にし、テストや保守性を向上させるため
>  * 理由2. クリーンアーキテクチャと比較して構成がシンプルで、保守性と理解容易性のバランスが良いため
>

<!-- NOTEで記した内容を基に、src/main/java/com/example以下のディレクトリをツリーで示す.  
* 機能ごとのパッケージ分割を行う
  * 例: src/main/java/com/example/user, src/main/java/com/example/order
  * 監視やログ、セキュリティなどの共通機能は、関心事ごとにパッケージを分割する
* src/main/java/com/example/manage, src/main/java/com/example/monitoring
-->
```plaintext
src/main/java/com/
    └── example
        ├── user
        │   ├── UserController.java
        │   ├── UserService.java
        │   ├── User.java
        │   └── UserRepository.java
        ├── order
        │   ├── OrderController.java
        │   ├── OrderService.java
        │   ├── Order.java
        │   └── OrderRepository.java
        └── monitoring
            └── MonitoringController.java
```

## ファイルのフォーマット

- 文字コード: UTF-8。
- インデント: スペース4つ。タブは使用しない。
- 改行コード: LF (Line Feed)
- ファイルの末尾に改行を追加する。