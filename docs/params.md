# 常见参数

| 参数              | 默认值 | 可用值 | 描述                   |
| ----------------- | ------ | ------ | ---------------------- |
| enable_nestloop   |        |        | 是否允许走nestloop连接 |
| enable_seqscan    |        |        | 是否走全表扫描         |
| enable_hashjoin   |        |        | 否允许走hash连接       |
| gp_autostats_mode |        |        | 是否收集统计信息       |



## Read More

- 第 19 章 服务器配置 - [19.7. 查询规划](http://www.postgres.cn/docs/10/runtime-config-query.html)
- [Greenplum优化--SQL调优篇](https://blog.csdn.net/u012948976/article/details/52695397)