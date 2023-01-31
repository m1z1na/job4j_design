package ru.job4j.jdbc;


import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {
    private Connection connection;
    private static final String DELIMETR = ";";
    private Properties properties;
    ;
    private String dump;

    public ImportDB(Properties properties, String dump) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        ;
        this.dump = dump;
        connection = initConnection();
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();

        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {

            for (String line = rd.readLine(); line != null; line = rd.readLine()) {
                String[] data = line.split(DELIMETR);
                if (data[0].isBlank() && data[1].isBlank()) {
                    users.add(new User(data[0], data[1]));
                }
            }

        }
        return users;
    }

    private Connection initConnection() throws ClassNotFoundException, SQLException {

        Class.forName(properties.getProperty("connection.driver_class"));
        String url = properties.getProperty("connection.url");
        String login = properties.getProperty("username");
        String password = properties.getProperty("password");
        return DriverManager.getConnection(url, login, password);

    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {

        for (User user : users) {
            try (PreparedStatement ps = connection.prepareStatement("insert into users(name, email) values (?, ?)")) {
                ps.setString(1, user.name);
                ps.setString(2, user.email);
                ps.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }


    public static void main(String[] args) throws Exception {

        ImportDB db = new ImportDB(classLoader(), "./dump.txt");
        db.save(db.load());
    }


    private static Properties classLoader() throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        }
        return config;
    }
}