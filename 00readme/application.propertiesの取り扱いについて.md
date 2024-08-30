# application.propertiesの取り扱いについて
```
workspace/ask-chat/src/main/resources/application.properties
```
に設置されるはずのファイル。個人設定バリバリに必要であるためにgitの追跡除外設定をしているので自力で作成してください

## コード内容

``` properties
# データベース接続のプロパティだYO
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/# 使用するスキーマ名を挿入
spring.datasource.username= # ySQLをインストールするときに作ったアカウント
spring.datasource.password= # MySQLをインストールするときに作ったアカウントのパスワード
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Hibernate設定
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
```