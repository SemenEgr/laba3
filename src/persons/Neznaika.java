package persons;

import items.Car;
import moods.MoodLevel;

public class Neznaika extends Character {
    public Neznaika(int mood) {
        super("Незнайка", mood);
    }

    @Override
    public void act(Character[] otherCharacters, Car car) {
        if (getMoodLevel() == MoodLevel.GOOD) {
            System.out.println(getName() + " выглядывает из окна и искренне говорит ему спасибо за аренду машины.");
        } else {
            System.out.println(getName() + " ничего не говорит Волшебнику.");
        }
    }

    public void actIncorrectMap(Character[] otherCharacters, Car car) {
        if (getMoodLevel() == MoodLevel.GOOD) {
            System.out.println(getName() + " говорит мотивирующие слова друзьям и предлагает все равно ехать.");
        } else {
            System.out.println(getName() + " грустно молчит.");
        }
    }
}