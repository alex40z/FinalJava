import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public interface Model {

    public ArrayList<Animal> getAnimals() throws IllegalArgumentException, InstantiationException;
    public HashSet<String> getAnimalCommands(int id) throws IllegalArgumentException;
    public void addAnimal(String className, String name, Date birthDate);
    public boolean addAnimalCommand(int id, String command);
}
