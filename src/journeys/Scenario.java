package journeys;

import errors.InvalidMapException;
import errors.MoodConflictException;
import items.Car;
import items.Map;
import persons.Character;
import persons.Neznaika;
import persons.Knopochka;
import persons.Pestrenkiy;
import persons.Wizard;
import moods.MoodLevel;

import java.util.Arrays;

public class Scenario {
    private final Character[] travelers;
    private final Wizard wizard;
    private final Car car;
    private Map map;

    public Scenario(Character[] travelers, Wizard wizard, Car car) {
        this.travelers = travelers;
        this.wizard = wizard;
        this.car = car;
    }

    public void start() {
        System.out.println("Путешествие в Цветочный город начинается\n");
        wizard.act(travelers, car);

        // Проверяем настроение волшебника
        if (wizard.getMood() >= 0 && wizard.getMood() <= 4) {
            System.out.println("Путешествие не началось, волшебник в тильте.");
            return;
        }

        try {
            map = wizard.chooseMap();
            System.out.println("Забыв о случайности выпадения карт в багажнике, Волшебник достает одну из них.");

            switch (map.getType()) {
                case "correct":
                    handleCorrectMap();
                    break;
                case "joke":
                    handleJokeMap();
                    break;
                case "incorrect":
                    handleIncorrectMap();
                    break;
            }
        } catch (InvalidMapException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (MoodConflictException e) {
            System.out.println("Конфликт настроения: " + e.getMessage());
        }
    }

    private void handleCorrectMap() {
        System.out.println("Карта оказывается правильной, на ней указаны города:");
        map.getCities().forEach(city -> System.out.println(" - " + city.name()));
        System.out.println("Волшебник без проблем находит на карте Цветочный город и прокладывает маршрут ребятам.");

        // Сначала выводим, что путешественники берут карту и садятся в машину
        System.out.println("Путешественники берут карту и садятся в машину.");
        car.interact(travelers[0]);

        // Затем выполняем действия путешественников
        executeTravelersActions();

        // Проверяем, включена ли музыка
        if (car.getMusic().equals("тишина")) {
            System.out.println("Так как ребята не включили музыку, в тишине машут Волшебнику и уезжают.");
        } else {
            System.out.println("Под " + car.getMusic() + " музыку из радио, машут Волшебнику и уезжают.");
        }
        System.out.println("Путешественники без проблем добрались до Цветочного города.");
    }

    private void handleJokeMap() {
        System.out.println("Карта оказывается шуточной, на ней указаны города:");
        map.getCities().forEach(city -> System.out.println(" - " + city.name()));
        System.out.println("Волшебник, не замечая этого, находит на карте Цветочный город и прокладывает маршрут ребятам.");

        // Сначала выводим, что путешественники берут карту и садятся в машину
        System.out.println("Путешественники берут карту и садятся в машину.");
        car.interact(travelers[0]);

        // Затем выполняем действия путешественников
        executeTravelersActions();

        // Проверяем, включена ли музыка
        if (car.getMusic().equals("тишина")) {
            System.out.println("Так как ребята не включили музыку, ребята в тишине машут Волшебнику и уезжают.");
        } else {
            System.out.println("Под " + car.getMusic() + " музыку из радио, машут Волшебнику и уезжают.");
        }
        System.out.println("По пути они оказались в непроходимых джунглях, ведь карта на самом деле вела в Травянистый город.");
        System.out.println("Ребята надолго здесь застряли.");
        System.out.println("Кто знает, что их теперь ждет...");
    }

    private void handleIncorrectMap() throws InvalidMapException {
        System.out.println("Карта оказывается неправильной, на ней указаны города:");
        map.getCities().forEach(city -> System.out.println(" - " + city.name()));
        System.out.println("Волшебник говорит ребятам про неудачу.");
        System.out.println("Путешественники сильно расстраиваются и теряют дух.");

        // Вывод списка путешественников в новом формате
        System.out.print("Путешественники: ");
        for (int i = 0; i < travelers.length; i++) {
            Character traveler = travelers[i];
            String moodDescription = traveler.getMoodLevel() == MoodLevel.GOOD ? "настроение хорошее" : "настроение плохое";
            System.out.print(traveler.getName() + ", " + moodDescription);
            if (i < travelers.length - 1) {
                System.out.print(". ");
            }
        }
        System.out.println();

        // Находим персонажей (без выброса исключений, так как они всегда присутствуют)
        Character neznaika = Arrays.stream(travelers)
                .filter(c -> c instanceof Neznaika)
                .findFirst()
                .get();
        Character knopochka = Arrays.stream(travelers)
                .filter(c -> c instanceof Knopochka)
                .findFirst()
                .get();
        Character pestrenkiy = Arrays.stream(travelers)
                .filter(c -> c instanceof Pestrenkiy)
                .findFirst()
                .get();

        neznaika.actIncorrectMap(travelers, car);
        knopochka.actIncorrectMap(travelers, car);
        pestrenkiy.actIncorrectMap(travelers, car);

        // Вариации исхода
        if (neznaika.getMoodLevel() == MoodLevel.GOOD) {
            System.out.println("Путешественники не берут карту и садятся в машину.");
            car.interact(travelers[0]);
            System.out.println("Так как ребята не включили музыку, они в тишине машут Волшебнику и уезжают.");
            System.out.println("По пути они оказались во всех городах из списка.");
            System.out.println("В общей сложности ребята проехали больше 50000 километров.");
            System.out.println("Очень уставшие, они приезжают в Цветочный город и ложатся на землю, громко выдыхая.");
        } else {
            System.out.println("Ребята никуда не едут, путешествие сорвалось.");
        }
    }

    private void executeTravelersActions() {
        // Вывод списка путешественников в новом формате
        System.out.print("Путешественники: ");
        for (int i = 0; i < travelers.length; i++) {
            Character traveler = travelers[i];
            String moodDescription = traveler.getMoodLevel() == MoodLevel.GOOD ? "настроение хорошее" : "настроение плохое";
            System.out.print(traveler.getName() + ", " + moodDescription);
            if (i < travelers.length - 1) {
                System.out.print(". ");
            }
        }
        System.out.println();

        // Находим персонажей (без выброса исключений, так как они всегда присутствуют)
        Character neznaika = Arrays.stream(travelers)
                .filter(c -> c instanceof Neznaika)
                .findFirst()
                .get();
        Character knopochka = Arrays.stream(travelers)
                .filter(c -> c instanceof Knopochka)
                .findFirst()
                .get();
        Character pestrenkiy = Arrays.stream(travelers)
                .filter(c -> c instanceof Pestrenkiy)
                .findFirst()
                .get();

        // Действия Незнайки
        try {
            neznaika.act(travelers, car);
        } catch (MoodConflictException e) {
            System.out.println("Конфликт настроения: " + e.getMessage());
        }

        // Действия Кнопочки
        try {
            knopochka.act(travelers, car);
        } catch (MoodConflictException e) {
            System.out.println("Конфликт настроения: " + e.getMessage());
        }

        // Действия Пестренького
        try {
            pestrenkiy.act(travelers, car);
        } catch (MoodConflictException e) {
            System.out.println("Конфликт настроения: " + e.getMessage());
        }
    }
}
