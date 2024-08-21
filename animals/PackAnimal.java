import java.util.Date;
import java.util.HashSet;

public abstract class PackAnimal extends Animal {

    public PackAnimal(String name, Date birthDate, HashSet<String> commandList) {
        super(name, birthDate, commandList);
    }
}
