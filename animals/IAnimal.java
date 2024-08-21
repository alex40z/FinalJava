import java.util.Date;
import java.util.HashSet;

public interface IAnimal {

    public String getType();
    public void setName(String name);
    public String getName();
    public void setBirthDate(Date birthDate);
    public Date getBirthDate();
    public boolean addCommand(String command);
    public HashSet<String> getCommands();
    public String toString();
}
