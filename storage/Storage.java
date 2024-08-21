import java.util.HashSet;

import org.json.JSONArray;

public interface Storage {

    public JSONArray getAnimals();
    public JSONArray getAnimalCommands(int id);
    public void addAnimal(Animal animal);
    public void updateAnimalCommands(int id, HashSet<String> commandsList);
}
