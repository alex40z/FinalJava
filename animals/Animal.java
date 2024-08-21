import java.util.Date;
import java.util.HashSet;

public abstract class Animal implements IAnimal {

    private String name;
    private Date birthDate;
    private HashSet<String> commandList;
    
    public Animal(String name, Date birthDate, HashSet<String> commandList) {
        this.name = name;
        this.birthDate = birthDate;
        this.commandList = commandList;
    }
   
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    
    public Date getBirthDate() {
        return birthDate;
    }

    public boolean addCommand(String command) {
        return commandList.add(command);
    }

    public HashSet<String> getCommands() {
        return commandList;
    }

    public String toString() {
        return String.format("Животное: %s %s, дата рождения: %tF", getType(), name, birthDate);
    }
}
