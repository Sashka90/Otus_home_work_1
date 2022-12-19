import animals.AbsAnimal;
import data.AnimalTypeData;
import data.CommandsData;
import factories.AnimalFactory;

import java.util.ArrayList;
import java.util.Scanner;

public class Runner {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        String command;
        String typeAnimal;
        ArrayList animals = new ArrayList();

        do {
            System.out.println("Выберите команду:\n " + CommandsData.ADD + "/" + CommandsData.LIST + "/" + CommandsData.EXIT);
            command = scanner.nextLine().trim().toUpperCase();

            switch (command) {
                case "ADD":
                    System.out.println("Какое животное?\n" +
                            AnimalTypeData.CAT + "/" + AnimalTypeData.DOG + "/" + AnimalTypeData.DUCK);
                    typeAnimal = scanner.nextLine().trim().toLowerCase();

                    AbsAnimal animal = null;

                    switch (AnimalTypeData.valueOf(typeAnimal.trim().toUpperCase())) {
                        case CAT:
                            animal = fillAnimalData(AnimalTypeData.CAT);
                            break;
                        case DOG:
                            animal = fillAnimalData(AnimalTypeData.DOG);
                            break;
                        case DUCK:
                            animal = fillAnimalData(AnimalTypeData.DUCK);
                            break;
                        default:
                            System.out.println("Неверный ввод, начните заново");
                    }

                    animal.say();
                    animals.add(animal);

                case "LIST":
                    for (int i = 0; i < animals.size(); i++)
                        System.out.println(animals.get(i));
                    break;

                case "EXIT":
                    System.exit(0);

                default:
                    System.out.println("Неверный ввод!");
            }
        }
        while (true);
    }

    private static AbsAnimal fillAnimalData(AnimalTypeData animalTypeData) {


        AnimalFactory animalFactory = new AnimalFactory();

        AbsAnimal animal = animalFactory.create(animalTypeData);
        System.out.println("Как зовут животное?");
        animal.setName(scanner.next());

        System.out.println("Какой цвет у животного?");
        animal.setColor(scanner.next());

        System.out.println("Какой возраст у животного?");
        String ageStr = scanner.next();
        while (!isNumber(ageStr)) {
            System.out.println("Введен неверный возраст");
            System.out.println("Повторите ввод");
            ageStr = scanner.next();
        }
        while (ageStr.equals("0") || ageStr.contains("-")) {
            System.out.println("Введен неверный возраст");
            System.out.println("Повторите ввод");
            ageStr = scanner.next();
        }

        animal.setAge(Integer.parseInt(ageStr));

        System.out.println("Какой вес у животного?");
        String weightStr = scanner.next();
        while (!isNumber(weightStr)) {
            System.out.println("Введен неверный вес");
            System.out.println("Повторите ввод");
            weightStr = scanner.next();
        }
        while (weightStr.equals("0") || weightStr.contains("-")) {
            System.out.println("Введен неверный вес");
            System.out.println("Повторите ввод");
            weightStr = scanner.next();
        }

        animal.setWeight(Integer.parseInt(weightStr));

        return animal;
    }

    private static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException ignoring) {
            return false;
        }
    }
}
