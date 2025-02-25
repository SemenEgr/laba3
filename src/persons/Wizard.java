package persons;

import errors.InvalidMapException;
import items.City;
import items.Map;
import items.Car;
import moods.MoodLevel;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Wizard extends Character {
    public Wizard(int mood) {
        super("Волшебник", mood);
    }

    @Override
    public void act(Character[] otherCharacters, Car car) {
        int mood = getMood();

        if (mood >= 5 && mood <= 10) {
            System.out.println(getName() + " подбежал к " + car.toString());
            car.interact(this);
        } else if (mood >= 0 && mood <= 4) {
            System.out.println(getName() + " грустно остался дома");
        }
    }
    public Map chooseMap() throws InvalidMapException {
        Random random = new Random();
        int mapType = random.nextInt(3); // 0 - correct, 1 - joke, 2 - incorrect

        List<City> cities;
        String type;
        switch (mapType) {
            case 0:
                cities = Arrays.asList(
                        new City("Арбузный город", false),
                        new City("Финансовый город", false),
                        new City("Пустынный город", false),
                        new City("Травянистый город", false),
                        new City("Цветочный город", false)
                );
                type = "correct";
                break;
            case 1:
                cities = Arrays.asList(
                        new City("Арбузный город", false),
                        new City("Финансовый город", false),
                        new City("Пустынный город", false),
                        new City("Травянистый город", false),
                        new City("Цветочный город", false)
                );
                type = "joke";
                break;
            default:
                cities = Arrays.asList(
                        new City("Санкт-Петербург", true),
                        new City("Кемерово", true),
                        new City("Москва", true),
                        new City("Вашингтон", true),
                        new City("Пекин", true)
                );
                type = "incorrect";
                break;
        }
        return new Map(cities, type);
    }

    @Override
    public void actIncorrectMap(Character[] otherCharacters, Car car) {
    }
}