package ru.job4j.jdbc;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private final Connection connection;
    private final Properties properties;


    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        connection = initConnection();
    }


    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (TableEditor table = new TableEditor(config)) {
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

    private Connection initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("connection.driver_class"));
        String url = properties.getProperty("connection.url");
        String login = properties.getProperty("username");
        String password = properties.getProperty("password");
        return DriverManager.getConnection(url, login, password);

    }

    public void createStatement(String query) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        }
    }

    public void createTable(String tableName) throws SQLException, ClassNotFoundException {
        String sql = String.format(
                "create table if not exists %s(%%s);".formatted(tableName),
                "id serial primary key");
        createStatement(sql);
    }

    public void dropTable(String tableName) throws SQLException {
        String sql = String.format(
                "drop table %s ", tableName);
        createStatement(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        String sql = String.format(
                "ALTER TABLE %s ADD COLUMN %s %s", tableName, columnName, type);
        createStatement(sql);
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        String sql = String.format(
                "ALTER TABLE %s DROP COLUMN %s", tableName, columnName);
        createStatement(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        String sql =
                String.format("ALTER TABLE %s RENAME COLUMN %s TO %s", tableName, columnName, newColumnName
                );
        createStatement(sql);
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

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}