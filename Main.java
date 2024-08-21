import view.View;

public class Main {

    public static void main(String[] args) {

        View view = new View();
        Storage storage = new MySqlStorage();
        Model model = new AnimalsModel(storage);

        Controller controller = new Controller(view, model);
        controller.dataProcessing();
    }
}
