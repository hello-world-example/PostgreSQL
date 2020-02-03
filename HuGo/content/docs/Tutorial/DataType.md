# 数据类型

PostgreSQL有着丰富的本地数据类型可用。**用户可以使用[CREATE TYPE](http://www.postgres.cn/docs/10/sql-createtype.html)命令为 PostgreSQL增加新的数据类型。**

| 名字                                        | 别名                     | 描述                                          |
| ------------------------------------------- | ------------------------ | --------------------------------------------- |
| `bigint`                                    | `int8`                   | **有符号的8字节整数**                         |
| `bigserial`                                 | `serial8`                | **自动增长的8字节整数**                       |
| `bit [ (*n*) ]`                             |                          | 定长位串                                      |
| `bit varying [ (*n*) ]`                     | `varbit`                 | 变长位串                                      |
| `boolean`                                   | `bool`                   | 逻辑布尔值（真/假）                           |
| `box`                                       |                          | 平面上的普通方框                              |
| `bytea`                                     |                          | 二进制数据（“字节数组”）                      |
| `character [ (*n*) ]`                       | `char [ (*n*) ]`         | 定长字符串                                    |
| `character varying [ (*n*) ]`               | `varchar [ (*n*) ]`      | **变长字符串**                                |
| `cidr`                                      |                          | IPv4或IPv6网络地址                            |
| `circle`                                    |                          | 平面上的圆                                    |
| `date`                                      |                          | **日历日期（年、月、日）**                    |
| `double precision`                          | `float8`                 | 双精度浮点数（8字节）                         |
| `inet`                                      |                          | IPv4或IPv6主机地址                            |
| `integer`                                   | `int`, `int4`            | **有符号4字节整数**                           |
| `interval [ *fields* ] [ (*p*) ]`           |                          | 时间段                                        |
| `json`                                      |                          | 文本 JSON 数据                                |
| `jsonb`                                     |                          | 二进制 JSON 数据，已分解                      |
| `line`                                      |                          | 平面上的无限长的线                            |
| `lseg`                                      |                          | 平面上的线段                                  |
| `macaddr`                                   |                          | MAC（Media Access Control）地址               |
| `macaddr8`                                  |                          | MAC (Media Access Control) 地址 (EUI-64 格式) |
| `money`                                     |                          | **货币数量**                                  |
| `numeric [ (*p*, *s*) ]`                    | `decimal [ (*p*, *s*) ]` | **可选择精度的精确数字**                      |
| `path`                                      |                          | 平面上的几何路径                              |
| `pg_lsn`                                    |                          | PostgreSQL日志序列号                          |
| `point`                                     |                          | 平面上的几何点                                |
| `polygon`                                   |                          | 平面上的封闭几何路径                          |
| `real`                                      | `float4`                 | 单精度浮点数（4字节）                         |
| `smallint`                                  | `int2`                   | **有符号2字节整数**                           |
| `smallserial`                               | `serial2`                | **自动增长的2字节整数**                       |
| `serial`                                    | `serial4`                | **自动增长的4字节整数**                       |
| `text`                                      |                          | **变长字符串**                                |
| `time [ (*p*) ] [ without time zone ]`      |                          | 一天中的时间（无时区）                        |
| `time [ (*p*) ] with time zone`             | `timetz`                 | 一天中的时间，包括时区                        |
| `timestamp [ (*p*) ] [ without time zone ]` |                          | **日期和时间（无时区）**                      |
| `timestamp [ (*p*) ] with time zone`        | `timestamptz`            | 日期和时间，包括时区                          |
| `tsquery`                                   |                          | 文本搜索查询                                  |
| `tsvector`                                  |                          | 文本搜索文档                                  |
| `txid_snapshot`                             |                          | 用户级别事务ID快照                            |
| `uuid`                                      |                          | 通用唯一标识码                                |
| `xml`                                       |                          | XML数据                                       |



## Read More

> - [第 8 章 数据类型](http://www.postgres.cn/docs/10/datatype.html)