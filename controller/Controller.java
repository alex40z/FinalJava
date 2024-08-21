import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import view.View;

public class Controller {

    private View view;
    private Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void dataProcessing() {
        while (true) {
            String command = view.getCommand();
            String[] command_parts = command.split(",");
            int id;
            switch (Integer.parseInt(command_parts[0])) {
                case 1:
                    ArrayList<Animal> animals;
                    try {
                        animals = model.getAnimals();
                        view.showAnimals(animals);
                    } catch (Exception e) {
                        view.showMessage(e.getMessage());
                    }
                    break;
                case 2:
                    id = Integer.parseInt(command_parts[1]);
                    HashSet<String> commandsList = model.getAnimalCommands(id);
                    view.showAnimalCommands(id, commandsList);
                    break;
                case 3:
                    String type = command_parts[1];
                    String name = command_parts[2];
                    try {
                        Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(command_parts[3]);
                        model.addAnimal(type, name, birthDate);
                        view.showMessage("Новое животное добавлено");
                    } catch (ParseException e) {
                        view.showMessage("Недопустимое значение даты");
                    }
                    break;
                case 4:
                    id = Integer.parseInt(command_parts[1]);
                    String animalCommand = command_parts[2];
                    if (model.addAnimalCommand(id, animalCommand)) {
                        view.showMessage("Новая команда добавлена");
                    } else {
                        view.showMessage("Такая команда уже есть");
                    }
                    break;
                case 0:
                    return;
            }
        }
    }
}
