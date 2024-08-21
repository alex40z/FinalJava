import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

public class Dog extends PetAnimal {

    public Dog(String name, Date birthDate, HashSet<String> commandsList) {
        super(name, birthDate, commandsList);
    }

    public Dog(String name, Date birthDate) {
        super(name, birthDate, new HashSet<String>(Arrays.asList("фас", "фу")));
    }

    public String getType() {
        return "собака";
    }
}
