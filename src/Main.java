import items.Car;
import journeys.Scenario;
import persons.Character;
import persons.Neznaika;
import persons.Knopochka;
import persons.Pestrenkiy;
import persons.Wizard;

public class Main {
    public static void main(String[] args) {
        // Задаем начальные настроения путешественников для тестирования сценариев
        Character[] travelers = {
                new Neznaika(10),
                new Knopochka(8),
                new Pestrenkiy(5)
        };

        Wizard wizard = new Wizard(7);
        Car car = new Car();

        Scenario scenario = new Scenario(travelers, wizard, car);
        scenario.start();
    }
}