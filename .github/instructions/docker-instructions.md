---
apply to: "Dockerfile,docker/docker-compose.yml"
---
# Dockerのコーディングガイドライン

全てのコードに[copilot-instructions.md](../copilot-instructions.md)の内容を適用する。

## Dockerfile

- **FROM**
  - ベースイメージは公式のもの、または信頼できるソースから選ぶ。
    - 例: `FROM python:3.9-slim`, `FROM node:14-alpine`
  - ベースイメージのバージョンが意図せず更新されないよう、具体的なバージョンを指定する。
    - 例: `FROM ubuntu:20.04`（`latest`は使用しない）
  - ビルドしたイメージのサイズを小さく保つため、可能な限り軽量なイメージを選ぶ。
  - マルチステージビルドを使用して、実行用イメージから開発ツールやビルドツールを除外する。
  - 実行用イメージにはdistroless等、最小限のランタイムイメージを使用する。
- **RUN**
  - コマンドは可能な限り1行にまとめる。
    - キャッシュを利用するため、頻繁に変更される部分は後に記述する。
    - 例: `RUN apt-get update && apt-get install -y package1 package2`
  - 不要なパッケージはインストールしない。
- **USER**
  - セキュリティのため、rootユーザーでの実行は避ける。
- **COPY/ADD**
  - 必要なファイルのみをコピーする。
  - `.dockerignore`を使用して、不要なファイルを除外する。
  - `COPY`を使用し、`ADD`はアーカイブの展開やURLからの取得が必要な場合に限る。
- **CMD/ENTRYPOINT**
  - Javaアプリケーションを実行するためのコマンドを指定する。 
    - 例: `CMD ["java", "-jar", "app.jar"]`