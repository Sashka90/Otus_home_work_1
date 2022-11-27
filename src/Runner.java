import animals.AbsAnimal;
import data.AnimalTypeData;
import data.CommandsData;
import factories.AnimalFactory;

import java.util.ArrayList;
import java.util.Scanner;

public class Runner {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        CommandsData.values().toString();
        AnimalTypeData.values().toString();
        String command;
        String typeAnimal;
        ArrayList animal = new ArrayList();

        do {
            System.out.println("Выберите команду:\n " + CommandsData.ADD + "/" + CommandsData.LIST + "/" + CommandsData.EXIT);
            command = scanner.nextLine().trim().toUpperCase();

            switch (command) {
                case "ADD":
                    System.out.println("Какое животное?\n" +
                            AnimalTypeData.CAT + "/" + AnimalTypeData.DOG + "/" + AnimalTypeData.DUCK);
                    typeAnimal = scanner.nextLine().trim().toLowerCase();

                    switch (typeAnimal) {
                        case "cat":
                            AbsAnimal cat = fillAnimalData(AnimalTypeData.CAT);
                            cat.say();
                            animal.add(cat);
                            continue;
                        case "dog":
                            AbsAnimal dog = fillAnimalData(AnimalTypeData.DOG);
                            dog.say();
                            animal.add(dog);
                            continue;
                        case "duck":
                            AbsAnimal duck = fillAnimalData(AnimalTypeData.DUCK);
                            duck.say();
                            animal.add(duck);
                            continue;
                        default:
                            System.out.println("Неверный ввод, начните заново");
                    }
                case "LIST":
                    for (int i = 0; i < animal.size(); i++)
                        System.out.println(animal.get(i));
                    break;

                case "EXIT":
                    System.exit(0);
            }
        }
        while (!command.equals("ADD") || !command.equals("LIST") || !command.equals("EXIT"));
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

        animal.setAge(Integer.parseInt(ageStr));

        System.out.println("Какой вес у животного?");
        String weightStr = scanner.next();
        while (!isNumber(weightStr)) {
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
