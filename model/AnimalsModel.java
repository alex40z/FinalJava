import java.lang.reflect.Constructor;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import org.json.JSONArray;
import org.json.JSONObject;

public class AnimalsModel implements Model {

    private Storage storage;

    public AnimalsModel(Storage storage) {
        this.storage = storage;    
    }

    public ArrayList<Animal> getAnimals() throws IllegalArgumentException, InstantiationException {
        ArrayList<Animal> animals = new ArrayList<Animal>();
        Animal animal;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        JSONArray jsonArray = storage.getAnimals();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String className = jsonObject.getString("class");
            Class<?> animalClass;
            try {
                animalClass = Class.forName(className);
            } catch (ClassNotFoundException e) {
                throw new IllegalArgumentException("Неизвестный класс животного: " + className);
            }
            Constructor<?> constructor;
            try {
                constructor = animalClass.getConstructor(String.class, Date.class, HashSet.class);
            } catch (NoSuchMethodException e) {
                throw new IllegalArgumentException("Не найден конструктор для класса животного: " + className);
            }
            Date birthDate;
            try {
                birthDate = formatter.parse(jsonObject.get("birth_date").toString());
            } catch (ParseException e) {
                throw new IllegalArgumentException("Ошибка преобразования даты");
            }
            HashSet<String> commandsList = new HashSet<String>(Arrays.asList(jsonObject.getString("commands_list").split(",")));
            try {
                animal = (Animal) constructor.newInstance(jsonObject.getString("name"), birthDate, commandsList);
            } catch (Exception e) {
                throw new InstantiationException("Ошибка создания экземпляра класса");
            }            
            animals.add(animal);
        }
        return animals;
    }

    public HashSet<String> getAnimalCommands(int id) throws IllegalArgumentException {
        JSONArray jsonArray = storage.getAnimalCommands(id);
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        HashSet<String> commandsList = new HashSet<String>(Arrays.asList(jsonObject.getString("commands_list").split(",")));
        return commandsList;
    }

    public void addAnimal(String type, String name, Date birthDate) {
        Animal animal;
        if (type.equals("кот")) {
            animal = new Cat(name, birthDate);
        } else if (type.equals("собака")) {
            animal = new Dog(name, birthDate);
        } else {
            throw new IllegalArgumentException("Неизвестный тип животного: " + type);
        }
        storage.addAnimal(animal); 
    }

    public boolean addAnimalCommand(int id, String command) {
        HashSet<String> commandsList = getAnimalCommands(id);
        if (!commandsList.add(command)) {
            return false;
        }
        storage.updateAnimalCommands(id, commandsList);
        return true;
    }
}
