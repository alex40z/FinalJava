import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

public class Cat extends PetAnimal {

    public Cat(String name, Date birthDate, HashSet<String> commandsList) {
        super(name, birthDate, commandsList);
    }

    public Cat(String name, Date birthDate) {
        super(name, birthDate, new HashSet<String>(Arrays.asList("кис-кис", "брысь")));
    }

    public String getType() {
        return "кот";
    }
}
