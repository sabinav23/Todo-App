package controller;

import model.Todo;
import repository.IRepository;

import java.util.List;

public class TodoController {

    private IRepository repository;

    public TodoController(IRepository repository){
        this.repository = repository;
    }

    public List<Todo> getAllTodos(){
        return this.repository.getAllTodos();
    }

    public void addTodo(String description){
        Todo todo = new Todo(description, false);
        this.repository.addTodo(todo);
    }

    public void toggleIsDone(Todo todo){
        this.repository.toggleIsDone(todo.getId());
    }
}
