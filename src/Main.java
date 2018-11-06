import controller.TodoController;
import repository.DatabaseRepository;
import repository.IRepository;
import view.TodoView;

public class Main {

    public static void main(String[] args) {
        IRepository repository = new DatabaseRepository();
        TodoController todocontroller = new TodoController(repository);

        TodoView todoview = new TodoView(todocontroller);
        todoview.start();
    }
}
