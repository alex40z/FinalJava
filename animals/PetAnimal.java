import java.util.Date;
import java.util.HashSet;

public abstract class PetAnimal extends Animal {

    public PetAnimal(String name, Date birthDate, HashSet<String> commandList) {
        super(name, birthDate, commandList);
    }
}
