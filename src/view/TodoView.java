package view;

import controller.TodoController;
import model.Todo;

import java.util.List;
import java.util.Scanner;

public class TodoView {

    private TodoController controller;
    private Scanner inputScanner;

    public TodoView(TodoController controller){
        this.controller = controller;
        this.inputScanner = new Scanner(System.in);
    }

    private void showMenu(){
        System.out.println("1. Show all Todos");
        System.out.println("2. Add Todo");
        System.out.println("3. Toggle Todo");
    }

    public void start(){
        this.showMenu();
        int option = this.inputScanner.nextInt();

        switch (option){
            case 1:
                this.showAllTodos();
                break;
            case 2:
                this.addTodo();
                break;
            case 3:
                this.toggleIsDone();
                break;
        }
    }


    private void showAllTodos(){
        this.printAllTodos();
        this.backOption();
    }

    private void printAllTodos() {
        List<Todo> todoList = this.controller.getAllTodos();
        for(int i = 0; i < todoList.size() ; i++){
            System.out.println(i + 1 + ". " + todoList.get(i));
        }
    }

    private void backOption(){
        System.out.println("1. Go back!");

        int option = this.inputScanner.nextInt();

        if(option == 1){
            this.start();
        }
    }

    private void addTodo(){
        System.out.println("Please enter Todo description: ");
        this.inputScanner.nextLine();
        String description = this.inputScanner.nextLine();
        this.controller.addTodo(description);
        this.showAllTodos();
    }

    private void toggleIsDone(){
        this.printAllTodos();
        System.out.println("Please insert Todo number");
        int option = this.inputScanner.nextInt();
        List<Todo> todoList = this.controller.getAllTodos();
        this.controller.toggleIsDone(todoList.get(option - 1));
        this.backOption();
    }
}
