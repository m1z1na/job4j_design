package ru.job4j.jdbc;


import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;


    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        connection = initConnection();
    }

    private Connection initConnection() throws ClassNotFoundException, SQLException {

        Class.forName(properties.getProperty("connection.driver_class"));
        String url = properties.getProperty("connection.url");
        String login = properties.getProperty("username");
        String password = properties.getProperty("password");
        return DriverManager.getConnection(url, login, password);

    }

    private static Properties classLoader() throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {


            config.load(in);
        }
        return config;
    }

    public void createTable(String tableName) throws SQLException, ClassNotFoundException {

        try (Statement statement = createStatement(connection)) {
            String sql = String.format(
                    "create table if not exists %s(%%s);".formatted(tableName),
                    "id serial primary key"
            );
            statement.execute(sql);

        }
    }

    public void dropTable(String tableName) {
        try (Statement statement = createStatement(connection)) {
            String sql = String.format(
                    "drop table %s ", tableName
            );
            statement.execute(sql);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void addColumn(String tableName, String columnName, String type) {
        try (Statement statement = createStatement(connection)) {

            String sql = String.format(
                    "ALTER TABLE %s ADD COLUMN %s %s", tableName, columnName, type);
            statement.execute(sql);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void dropColumn(String tableName, String columnName) {
        try (Statement statement = createStatement(connection)) {

            String sql = String.format(
                    "ALTER TABLE %s DROP COLUMN %s", tableName, columnName);
            statement.execute(sql);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {

        try (Statement statement = createStatement(connection)) {

            String sql =
                    String.format("ALTER TABLE %s RENAME COLUMN %s TO %s", tableName, columnName, newColumnName
                    );
            statement.executeUpdate(sql);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) throws Exception {


        try (TableEditor table = new TableEditor(classLoader())) {

            table.createTable("supertest");
            System.out.println(table.getTableScheme("supertest"));
            table.addColumn("supertest", "age", "INT");
            System.out.println(table.getTableScheme("supertest"));
            table.renameColumn("supertest", "age", "newage");
            System.out.println(table.getTableScheme("supertest"));
            table.dropColumn("supertest", "newage");
            System.out.println(table.getTableScheme("supertest"));
            table.dropTable("supertest");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    private Statement createStatement(Connection connection) throws SQLException {
        return connection.createStatement();
    }
}