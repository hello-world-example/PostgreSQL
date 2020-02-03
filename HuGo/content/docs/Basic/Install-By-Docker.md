# 通过 Docker 安装



```bash
# 启动，默认账户: postgres/postgres
$ docker run -d --restart=always \
  -p 5432:5432 \
  -e "POSTGRES_PASSWORD=postgres" \
  --name postgres-9.6 \
  postgres:9.6.15
  

# 进入 psql
$ docker exec -it postgres-9.6 psql -U postgres
# 退出 psql
\q
```



## Read More

- Docker Hub [postgres](https://hub.docker.com/_/postgres)