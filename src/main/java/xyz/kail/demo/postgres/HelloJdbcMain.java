package xyz.kail.demo.postgres;

import java.sql.*;

public class HelloJdbcMain {


    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        /*
         * 1. 注册驱动
         * https://jdbc.postgresql.org/documentation/head/load.html
         *
         * org.postgresql.Driver.register();
         */
        Class.forName("org.postgresql.Driver");


        /*
         * ❤❤❤ Connecting to the Database：
         * ❤❤❤ https://jdbc.postgresql.org/documentation/head/connect.html
         */
        String url = "jdbc:postgresql://localhost:5432/kail";
        try (Connection connection = DriverManager.getConnection(url, "postgres", "")) {

            /*
             * Chapter 5. Issuing a Query and Processing the Result
             * https://jdbc.postgresql.org/documentation/head/query.html
             */
            String querySQL = "select * from test limit 1";
            try (PreparedStatement preparedStatement = connection.prepareStatement(querySQL)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    for (; resultSet.next(); ) {
                        long id = resultSet.getLong("ID");
                        System.out.println(id);
                    }
                }
            }
        }
    }
}
