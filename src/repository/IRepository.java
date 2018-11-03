package repository;

import model.Todo;

import java.util.List;

public interface IRepository {
    List<Todo> getAllTodos();
    void addTodo(Todo todo);
    void toggleIsDone(int id);
}
