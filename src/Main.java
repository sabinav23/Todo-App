import controller.TodoController;
import repository.IRepository;
import repository.InMemoryRepository;
import view.TodoView;

public class Main {

    public static void main(String[] args) {
        IRepository repository = new InMemoryRepository();

        TodoController todocontroller = new TodoController(repository);

        TodoView todoview = new TodoView(todocontroller);
        todoview.start();
    }
}
