package connection;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class databaseConnection {


    private static databaseConnection instance;

    private final String url = "jdbc:mysql://localhost:3306/magazin?createdatabaseIfNotExist=true";
    private final String username = "root";
    private final String password = "password";

    private final Connection connection;

    public databaseConnection() throws SQLException {
        this.connection = DriverManager.getConnection(url, username, password);
    }

    public static databaseConnection getInstance() throws SQLException {
        if (instance == null) {
            System.out.println("Intrat");
            instance = new databaseConnection();
        }

        if (instance.getConnection().isClosed()) {
            instance = new databaseConnection();
        }

        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    private static void createTables(Connection connection) {
        String delimiter = ";";
        try (Scanner scanner = new Scanner(new File("table.sql"))) {
            scanner.useDelimiter(delimiter);
            while (scanner.hasNext()) {
                String rawStatement = scanner.next() + delimiter;
                if (rawStatement.trim().length() < 2) {
                    continue;
                }
                System.out.println("Executing statement: " + rawStatement);

                try (Statement currentStatement = connection.createStatement()) {
                    currentStatement.execute(rawStatement);
                    System.out.println("Task succesfully executed!");
                } catch (SQLException e) {
                    System.out.println("Cannot execute task!" + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't load folder!");
        }
    }

}
