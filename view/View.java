package view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class View {

    private Scanner in;

    public View() {
        in = new Scanner(System.in, "cp866");
    }

    public String getCommand() {
        int intValue;
        String strValue;
        String command;
        while (true) {
            System.out.println("Основное меню:");
            System.out.println("1. Показать список животных");
            System.out.println("2. Показать список команд животного");
            System.out.println("3. Добавить новое животное");
            System.out.println("4. Обучить животное новой команде");
            System.out.println("0. Завершить работу");
            System.out.print("Введите команду: ");
            try {
                intValue = in.nextInt();
                if (intValue < 0 || intValue > 4) {
                    throw new InputMismatchException();
                }
                command = String.valueOf(intValue);
                if (intValue == 2) {
                    System.out.print("Введите id животного: ");
                    intValue = in.nextInt();
                    command = command + "," + String.valueOf(intValue);
                } else if (intValue == 3) {
                    in.nextLine();
                    System.out.print("Введите тип животного: ");
                    strValue = in.nextLine();
                    command = command + "," + strValue;
                    System.out.print("Введите имя животного: ");
                    strValue = in.nextLine();
                    command = command + "," + strValue;
                    System.out.print("Введите дату рождения животного (ГГГГ-ММ-ДД): ");
                    strValue = in.nextLine();
                    command = command + "," + strValue;
                } else if (intValue == 4) {
                    in.nextLine();
                    System.out.print("Введите id животного: ");
                    strValue = in.nextLine();
                    command = command + "," + strValue;
                    System.out.print("Введите новую команду: ");
                    strValue = in.nextLine();
                    command = command + "," + strValue;
                }
                return command;
            } catch (InputMismatchException e) {
                System.out.println("Недопустимое значение!");
                in.next();
            }
        }
    }

    public <Animal> void showAnimals(ArrayList<Animal> animals) {
        for (int i = 0; i < animals.size(); i++) {
            System.out.println(animals.get(i).toString());
        }
    }

    public void showAnimalCommands(int id, HashSet<String> commandsList) {
        System.out.println(String.format("id животного: %d, команды: %s", id, String.join(",", commandsList)));
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
