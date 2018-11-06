package repository;

import model.Todo;
import java.util.ArrayList;
import java.util.List;

public class InMemoryRepository implements IRepository{

    private List<Todo> todos;
    private int nextId;

    public InMemoryRepository(){
        this.todos = new ArrayList<>();
        this.nextId = 0;
    }

    @Override
    public List<Todo> getAllTodos() {
        return this.todos;
    }

    @Override
    public void addTodo(Todo todo) {
        todo.setId(this.nextId++);
        this.todos.add(todo);
    }

    @Override
    public void toggleIsDone(int id){
        for(Todo todo: this.todos){
            if(todo.getId() == id){
                todo.setDone(!todo.isDone());
            }
        }
    }
}
