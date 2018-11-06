package repository;

import model.Todo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileRepository implements IRepository {

    private static final String FILE_NAME = "C:/Users/MuRy/IdeaProjects/Todo-App/src/persistence/todoList.txt";

    private int nrID;

    public FileRepository() {
        this.nrID = 0;
    }

    @Override
    public List<Todo> getAllTodos() {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream(FileRepository.FILE_NAME);
            objectInputStream = new ObjectInputStream(fileInputStream);

            return  (List<Todo>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (fileInputStream != null){
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void addTodo(Todo todo) {
        List<Todo> existentTodos = this.getAllTodos();
        todo.setId(nrID++);
        existentTodos.add(todo);

        this.saveTodos(existentTodos);
    }

    private void saveTodos(List<Todo> existentTodos) {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(FileRepository.FILE_NAME);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(existentTodos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void toggleIsDone(int id) {
        List<Todo> existentTodos = this.getAllTodos();
        for(Todo todo : existentTodos){
            if(todo.getId() == id){
                todo.setDone(!todo.isDone());
            }
        }
        this.saveTodos(existentTodos);
    }
}
