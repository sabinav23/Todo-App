package repository;

import model.Todo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseRepository implements IRepository {

    private static final String DATABASE_URL = "jdbc:mysql://localhost/todoapp?"
            + "user=user&password=password";
    private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public DatabaseRepository(){
        // This will load the MySQL driver, each DB has its own driver
        try {
            Class.forName(DATABASE_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Todo> getAllTodos() {
        List<Todo> todoList = new ArrayList<>();

        try {
            // Setup the connection with the DB
            this.createConnection();

            // Statements allow to issue SQL queries to the database
            this.statement = this.connection.createStatement();
            // Result set get the result of the SQL query
            this.resultSet = this.statement
                    .executeQuery("select * from todo");

            while(this.resultSet.next()) {
                Todo todo = new Todo(this.resultSet.getString("todo_description"), this.resultSet.getBoolean("is_done"));
                todo.setId(this.resultSet.getInt("id"));
                todoList.add(todo);
            }
        } catch (SQLException e1) {
                e1.printStackTrace();
        } finally {
            this.cleanSelectStatement();
        }
        return todoList;
    }

    private void cleanSelectStatement() {
        try {
            if (this.connection != null) {
                this.connection.close();
            }
            if (this.statement != null) {
                this.statement.close();
            }
            if (this.resultSet != null) {
                this.resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addTodo(Todo todo) {

        try {
            // Setup the connection with the DB
            this.createConnection();

            // Statements allow to issue SQL queries to the database
            this.preparedStatement = this.connection.prepareStatement("INSERT INTO Todo(todo_description, is_done) VALUES(?, ?)");
            this.preparedStatement.setString(1, todo.getDescription());
            this.preparedStatement.setBoolean(2, todo.isDone());
            this.preparedStatement.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            this.cleanPreparedStatement();
        }
    }

    private void cleanPreparedStatement() {
        try {
            if (this.connection != null) {
                this.connection.close();
            }
            if (this.preparedStatement != null) {
                this.preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void toggleIsDone(int id) {
        try {
            this.createConnection();

            // Statements allow to issue SQL queries to the database
            this.preparedStatement = this.connection.prepareStatement("UPDATE Todo SET is_done = !is_done WHERE id = ?");
            this.preparedStatement.setInt(1, id);
            this.preparedStatement.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            this.cleanPreparedStatement();
        }
    }

    private void createConnection() throws SQLException {
        // Setup the connection with the DB
        this.connection = DriverManager
                .getConnection(DATABASE_URL);
    }
}
