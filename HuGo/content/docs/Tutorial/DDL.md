# DDL 数据定义



## 表基础

```sql
CREATE TABLE products (
    product_no integer,
    name text,
    price numeric
);
```
> SQL 命令 [CREATE TABLE](http://www.postgres.cn/docs/10/sql-createtable.html)
>

## 默认值
```sql
CREATE TABLE products (
    product_no integer DEFAULT nextval('products_product_no_seq'),
    name text,
    price numeric DEFAULT 9.99,
    create_time timestamp DEFAULT CURRENT_TIMESTAMP
);
```



```sql
CREATE TABLE products (
    product_no SERIAL,
    ...
);
```



## 约束

### CHECK

```sql
CREATE TABLE products (
    product_no integer,
    name text,
    price numeric CHECK (price > 0)
);
```

给与约束一个独立的名称

```sql
CREATE TABLE products (
    product_no integer,
    name text,
    price numeric CONSTRAINT positive_price CHECK (price > 0)
);
```

一个检查约束也可以引用多个列

```sql
CREATE TABLE products (
    product_no integer,
    name text,
    price numeric CHECK (price > 0),
    discounted_price numeric,
    CHECK (discounted_price > 0 AND price > discounted_price)
);
```

> 前两个约束称为列约束，而第三个约束为表约束，因为它独立于任何一个列定义

### 非空约束

```sql
CREATE TABLE products (
    product_no integer NOT NULL,
    name text NOT NULL,
    price numeric
);
```

### 唯一约束

列约束
```sql
CREATE TABLE products (
    product_no integer [CONSTRAINT must_be_different] UNIQUE,
    name text,
    price numeric
);
```
表约束
```sql
CREATE TABLE products (
    product_no integer,
    name text,
    price numeric,
    UNIQUE (product_no)
);
```
多列约束
```sql
CREATE TABLE example (
    a integer,
    b integer,
    c integer,
    UNIQUE (a, c)
);
```

### 主键

```sql
CREATE TABLE products (
    product_no integer PRIMARY KEY,
    name text,
    price numeric
);
```

或

```sql
CREATE TABLE example (
    a integer,
    b integer,
    c integer,
    PRIMARY KEY (a, c)
);
```

> 增加一个主键将自动在主键中列出的 列或列组 上创建一个唯一 `B-tree` 索引。并且会强制这些列被标记为`NOT NULL`。
>
> 一个表最多只能有一个主键。关系数据库理论要求每一个表都要有一个主键。但PostgreSQL中并未强制要求这一点，但是最好能够遵循它。
>
> 主键对于文档和客户端应用都是有用的。例如，一个允许修改行值的 GUI 应用可能需要知道一个表的主键，以便能唯一地标识行。如果定义了主键，数据库系统也有多种方法来利用主键。例如，主键定义了外键要引用的默认目标列。

## 系统列

> 每一个表都拥有一些由系统隐式定义的*系统列*。因此，这些列的名字不能像用户定义的列一起使用。 事实上用户不需要关心这些列，只需要知道它们存在即可。

- `oid` : 一行的对象标识符（对象ID）
- `tableoid`
- `xmin`
- `cmin`
- `xmax`
- `cmax`
- `ctid`

> http://www.postgres.cn/docs/10/ddl-system-columns.html

## 修改表

```sql
ALTER TABLE products ADD COLUMN description text;

ALTER TABLE products ADD COLUMN description text CHECK (description <> '');


ALTER TABLE products DROP COLUMN description;


ALTER TABLE products ALTER COLUMN price SET DEFAULT 7.77;
ALTER TABLE products ALTER COLUMN price DROP DEFAULT;
```

## Schema 模式

> 在SQL标准中没有 `public` 模式的概念。为了最大限度的与标准一致，我们不应使用（甚至是删除）`public`模式。
>
> 某些SQL数据库系统可能根本没有实现模式，或者提供允许跨数据库访问的名字空间。如果需要使用这样一些系统，最好不要使用模式。

- Database
  - Schema
    - Table

```sql
CREATE SCHEMA myschema;
```

在一个模式中创建或访问对象，需要使用由模式名和表名构成的限定名，模式名和表名之间以点号分隔：

```sql
CREATE TABLE myschema.mytable (
 ...
);
```

### search_path

```sql
SHOW search_path;

SET search_path TO myschema,public;
```

### 内建 Schema

除`public`和用户创建的模式之外，每一个数据库还包括一个`pg_catalog`模式，它包含了系统表和所有内建的数据类型、函数以及操作符。`pg_catalog`总是搜索路径的一个有效部分。如果没有在路径中显式地包括该模式，它将在路径中的模式**之前**被搜索。这保证了内建的名称总是能被找到。然而，如果我们希望用用户定义的名称重载内建的名称，可以显式的将`pg_catalog`放在搜索路径的末尾。

由于系统表名称以`pg_`开头，最好还是避免使用这样的名称，以避免和未来新版本中 可能出现的系统表名发生冲突。系统表将继续采用以`pg_`开头的方式，这样它们不会 与非限制的用户表名称冲突。

## 继承

> 一个表可以继承另一个表的所有列：http://www.postgres.cn/docs/10/ddl-inherit.html

## 表分区

表分区指：将逻辑上的一个大表分成一些小的物理上的片

- 在某些情况下查询性能能够显著提升，特别是当那些访问压力大的行在一个分区或者少数几个分区时。划分可以取代索引的主导列、减小索引尺寸以及使索引中访问压力大的部分更有可能被放在内存中。
- 当查询或更新访问一个分区的大部分行时，可以通过该分区上的一个顺序扫描来取代分散到整个表上的索引和随机访问，这样可以改善性能。
- 如果需求计划使用划分设计，可以通过增加或移除分区来完成批量载入和删除。 执行`ALTER TABLE DETACH PARTITION`或者使用`DROP TABLE` 删除一个单独的分区都远快于一个批量操作。这些命令也完全避免了由批量`DELETE`造成的`VACUUM`负载。
- 很少使用的数据可以被迁移到便宜且较慢的存储介质上。

## 其他特性

- [外部数据](http://www.postgres.cn/docs/10/ddl-foreign-data.html)
- [其他数据库对象](http://www.postgres.cn/docs/10/ddl-others.html)

## Read More

> - [**第 5 章 数据定义**](http://www.postgres.cn/docs/10/ddl.html)





