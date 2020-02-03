# catalogs 系统表

> 官方文档： [第 51 章 系统目录](http://www.postgres.cn/docs/10/catalogs.html)



## [表] 隐藏字段

> 来自： [PostgreSQL -- 表中的隐含字段](https://blog.51cto.com/13126942/2050015)

- `oid` ： 行标示符 
  - 创建表的时候使用了 `WITH OIDS`，或者是设置了配置参数 `default_with_oids` 时出现
  - 字段的类型是 `oid` ，和字段同名
  - `OID` 是32位的量，有可能重叠
- `tableoid` : 所在的 `oid`，对应 `pg_class` 中的 `oid`。从继承层次中选取的查询特别有用
- `xmin`: 插入该行版本的事务的标识(事务ID)
- `cmin`: 在插入事务内部的命令标识(从零开始)。
- `xmax`: 删除事务的标识(事务ID)，如果不是被删除的行版本，那么是零。
- `cmax`: 在删除事务内部的命令标识符，或者是零。
- `ctid`: 一个行版本在它所处的表内的物理位置



```sql
select tableoid::regclass as tablename,
       oid, tableoid, cmax, cmin, xmin, xmax, ctid
from pg_database;
-- ------------------------------
select attr.*
from pg_class class,
     pg_attribute attr
where 1 = 1
  and class.relname = '表名'
  and class.oid = attr.attrelid -- and attr.attstattarget = 0;
```



## [表] pg_database

所有的数据库

- `datname` 数据库名



## [表] pg_namespace

当前数据库的 Schema

- `nspname` schema 的名字
- `nspowner` schema 所有者 id `pg_authid.oid`  |  `pg_get_userbyid(xx)` 



## [表] pg_class

> http://www.postgres.cn/docs/10/catalog-pg-class.html

`relkind`  字段标明，当前对象的类型：`r` = 普通表，`i` = 索引， `S` = 序列，`t` = TOAST 表，`v` = 视图， `m` = 物化视图， `c` = 组合类型， `f` = 外部表， `p` = 分区表

- `relname` 表、索引、视图 等，名字
- `relnamespace` ： `pg_namespace.oid`



## [表] pg_attribute

> - http://www.postgres.cn/docs/10/catalog-pg-attribute.html

表的字段的信息

- `attrelid` 字段所属的表，对应于 `pg_class.oid`
- `attname` 字段名字
- `atttypid` 这个字段的数据类型,对应于 `pg_type.oid`
- `attlen` 字段长度，`pg_type.typlen` 的拷贝
- `attnotnull` 这代表一个非空约束。我们可以改变这个字段以打开或者关闭这个约束



## [表] pg_type

数据类型的信息

- `typname` 数据类型名字
- `typlen` 
  - 定长类型：是该类型内部表现形式的字节数目
  - 变长类型，typlen 是负数
    - -1 表示一种"变长"类型（有长度字属性的数据）
    - -2 表示这是一个 NULL 结尾的 C 字串。



## [表] pg_description

数据库中对象（表、字段等）的注释。

- `objoid` 表 、表中字段的注释，对应 `pg_class.oid`
- `objsubid` 对于一个表字段的注释，它是字段号，对应于`pg_attribute.attnum`，表字段类型是 0
- `description`  表、字段 注释



# 常用 SQL

## 表结构

```sql
SELECT ns.nspname,                                  -- schemaName
       cla.OID,
       cla.relname,                                 -- tableName
       attr.attname,                                -- 列名
       attr.attlen,                                 -- 列类型长度长度
       attr.attnotnull,                             -- 非空
       attr.attnum,                                 -- 列顺序
       tp.typname,                                  -- 类型名
       format_type(attr.atttypid, attr.atttypmod),
       col_description(attr.attrelid, attr.attnum), -- 列注释
       def.adsrc                                    -- 默认值
from pg_namespace ns
         inner join pg_class cla on cla.relnamespace = ns.OID
         inner join pg_attribute attr on attr.attrelid = cla.OID and attr.attnum > 0
         inner join pg_type tp on attr.atttypid = tp.OID
         left outer join pg_attrdef def on def.adrelid = cla.OID and def.adnum = attr.attnum
where 1 = 1
  and ns.nspname = 'ttpai_boss_v1'
  and cla.relname = 'boss_account'
order by ns.nspname, cla.relname, attr.attnum
```



## 表注释

```sql
SELECT clas.oid,
       clas.relname    AS table_name,
       dsk.description AS comment
FROM pg_class clas
         LEFT JOIN pg_description dsk ON dsk.objsubid = 0 AND clas.oid = dsk.objoid
WHERE clas.relnamespace = (SELECT oid FROM pg_namespace WHERE nspname = 'schemaName')
  AND clas.relkind = 'r'
ORDER BY clas.relname
```



## 确认一个对象是表还是视图

```sql
select ns.nspname,
       clas.relname,
       clas.relkind
from pg_namespace ns,
     pg_class clas
where clas.relnamespace = ns.OID
  and ns.nspname = 'schemaName'
  and clas.relname = 'tableName'
```





# Read More

- [postgresql用sql语句查询表结构](https://www.cnblogs.com/jxycn/p/5215822.html)