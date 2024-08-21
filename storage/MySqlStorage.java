import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashSet;

import org.json.JSONArray;
import org.json.JSONObject;

public class MySqlStorage implements Storage {

    private String url = "jdbc:mysql://localhost/farm";
    private String username = "root";
    private String password = "";

    public JSONArray getAnimals() {
        return getData("SELECT * FROM animals");
    }

    public JSONArray getAnimalCommands(int id) {
        return getData(String.format("SELECT commands_list FROM animals WHERE id = %d", id));
    }

    public void addAnimal(Animal animal) {
        saveData(String.format("INSERT INTO animals (class, name, birth_date, commands_list) values ('%s', '%s', '%s', '%s')",
            animal.getClass().getName(), animal.getName(), 
            new SimpleDateFormat("yyyy-MM-dd").format(animal.getBirthDate()),
            String.join(",", animal.getCommands())));
    }

    public void updateAnimalCommands(int id, HashSet<String> commandsList) {
        saveData(String.format("UPDATE animals SET commands_list = '%s' WHERE id = %d",
            String.join(",", commandsList), id));
    }

    private JSONArray getData(String sql) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int totalColumns = resultSet.getMetaData().getColumnCount();
            JSONArray jsonArray = new JSONArray();
            while (resultSet.next()) {                
                JSONObject jsonObject = new JSONObject();
                for (int i = 0; i < totalColumns; i++) {
                    jsonObject.put(resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase(), resultSet.getObject(i + 1) != null ? resultSet.getObject(i + 1) : "");
                }
                jsonArray.put(jsonObject);
            }
            return jsonArray;
        } catch(Exception ex) {
            System.out.println("Connection failed");
            System.out.println(ex);
            return null;
        }
    }

    private void saveData(String sql) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
        } catch(Exception ex) {
            System.out.println("Connection failed");
            System.out.println(ex);
        }
    }
}
