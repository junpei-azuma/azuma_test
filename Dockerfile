# ビルドステージ: Amazon Corretto JDKを使用
FROM amazoncorretto:21.0.7 AS build

WORKDIR /workspace/app

# 最初にビルド設定ファイルをコピーすることにより、レイヤーキャッシュを活用
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
# Gradleの依存関係を事前に解決することで、ソースコード変更時のビルド時間を短縮
RUN chmod +x ./gradlew && \
    ./gradlew dependencies --no-daemon && \
    yum install -y binutils && yum clean all
COPY src src
RUN ./gradlew bootJar --no-daemon
# カスタムJREを作成する
RUN jlink \
    --module-path /usr/lib/jvm/java-21-amazon-corretto/lib \
    --add-modules java.base,java.desktop,java.instrument,java.management,java.naming,java.net.http,java.security.jgss,java.sql,jdk.jfr,jdk.unsupported \
    --strip-debug \
    --compress 2 \
    --no-man-pages \
    --no-header-files \
    --output ./jre-custom

# 実行ステージ: distrolessイメージを使用
FROM gcr.io/distroless/java-base-debian12:nonroot

WORKDIR /app

# セキュリティのための最小限の権限設定
USER nonroot:nonroot

# ビルドステージから必要なファイルのみをコピー
COPY --from=build --chown=nonroot:nonroot /workspace/app/jre-custom /jre
COPY --from=build --chown=nonroot:nonroot /workspace/app/build/libs/my-application.jar /app.jar

# アプリケーションポートを公開
EXPOSE 8080

# アプリケーション実行
ENTRYPOINT ["/jre/bin/java", "-jar", "/app.jar"]
