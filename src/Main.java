import controller.TodoController;
import repository.FileRepository;
import repository.IRepository;
import view.TodoView;

public class Main {

    public static void main(String[] args) {
        IRepository repository = new FileRepository();
        TodoController todocontroller = new TodoController(repository);

        TodoView todoview = new TodoView(todocontroller);
        todoview.start();
    }
}
