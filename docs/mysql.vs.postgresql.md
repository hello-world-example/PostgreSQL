# MySQL vs PostgreSQL



|      | MySQL                   | PostgreSQL                |
| ---- | ----------------------- | ------------------------- |
|      | 号称最流行              | 号称最先进                |
|      | `text` 字段有不同的限制 | `text` 能支持各种大小     |
|      | 单表自增                | 多个表从同一个序列中取 ID |
|      | 可以用多级从库          | 9.2之前，不能用从库带从库 |
|      |                         |                           |



## PostgreSQL 高级特性

- 可以在  `array` 和` json` 上建索引，甚至还能用表达式索引
- **NoSQL**：JSON，JSONB，XML，HStore原生支持，至NoSQL数据库的外部数据包装器。
- 支持服务器端脚本
- 地理信息处理(`GIS`)
- 高效处理树结构
- 可以把 70 种外部数据源 (包括 MySQL, Oracle, **CSV**, **hadoop** ...) 当成自己数据库中的表来查询
- **图数据**：递归查询，高效处理图结构, 轻松实现 "朋友的朋友的朋友" 
- **缓存**：物化视图
- **数据仓库**：能平滑迁移至同属Pg生态的GreenPlum，DeepGreen，HAWK等，使用FDW进行ETL
- **OLAP**：citus分布式插件，ANSI SQL兼容，窗口函数，CTE，CUBE等高级分析功能，任意语言写UDF
- **搜索索引**：全文搜索索引足以应对简单场景；丰富的索引类型，支持函数索引，条件索引
- **时序数据**：timescaledb时序数据库插件，分区表，BRIN索引



## Read More

- [“王者对战”之 MySQL 8 vs PostgreSQL 10](https://www.oschina.net/translate/showdown-mysql-8-vs-postgresql-10)
- [MySQL · 源码分析 · InnoDB Repeatable Read隔离级别之大不同](http://mysql.taobao.org/monthly/2017/06/07/)
- [PostgreSQL 与 MySQL 相比，优势何在？](https://www.zhihu.com/question/20010554)