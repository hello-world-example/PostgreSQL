# MAC 下安装 Postgres

## 安装

1. 进入下载页面：https://www.postgresql.org/download/
2. 选择 macOS：https://www.postgresql.org/download/macosx/
3. 选择 Postgres.app：https://postgresapp.com/
4. 选择指定版本下载，这里下载的是 PostgreSQL 11
   1. 下载后双击打开
   2. 拖动到 Applications 文件夹
   3. 安装成功
5. 双击打开 Postgres 应用
   1. 点击 `Initialize` 即可开启 Postgres 服务
6. 默认安装目录
   1. /Applications/Postgres.app/Contents/Versions/latest/bin
7. 默认数据文件目录
   1. /Users/kail/Library/Application Support/Postgres/var-11
8. 默认链接方式
   1. `localhost:5432`
   2. Database`postgres`
   3. User: `postgres`
   4. Password `None`
   5. Connection URL `postgresql://localhost`

## 卸载

```bash
删除 Postgres app

# 删除配置
$ rm -rf /Users/kail/Library/Application\ Support/Postgres
```



## Read More

- https://postgresapp.com/
  - Graphical Clients
    - [pgAdmin 4](https://www.pgadmin.org/)
    - Postico 
  - How to connect
    - [PostgreSQL JDBC driver](https://jdbc.postgresql.org/download.html)
    - [official PostgreSQL JDBC documentation](https://jdbc.postgresql.org/documentation/head/index.html)

## Debian Linux

> 阮一峰 [PostgreSQL新手入门](http://www.ruanyifeng.com/blog/2013/12/getting_started_with_postgresql.html)

