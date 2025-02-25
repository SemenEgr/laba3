package persons;

import items.Car;
import moods.MoodLevel;

public class Knopochka extends Character {
    public Knopochka(int mood) {
        super("Кнопочка", mood);
    }

    @Override
    public void act(Character[] otherCharacters, Car car) {
        if (getMoodLevel() == MoodLevel.GOOD) {
            System.out.println(getName() + " включает веселую музыку на радио.");
            car.setMusic("веселая");
        } else {
            System.out.println(getName() + " громко говорит Пестренькому включить грустную музыку.");
        }
    }

    @Override
    public void actIncorrectMap(Character[] otherCharacters, Car car) {
        Character neznaika = null;

        for (Character c : otherCharacters) {
            if (c instanceof Neznaika) {
                neznaika = c;
                break;
            }
        }

        if (neznaika == null) {
            throw new IllegalStateException("Незнайка не найден");
        }

        if (getMoodLevel() == MoodLevel.GOOD && neznaika.getMoodLevel() == MoodLevel.GOOD) {
            System.out.println(getName() + " поддерживает Незнайку, говорит, что знает маршрут.");
        } else if (getMoodLevel() == MoodLevel.GOOD && neznaika.getMoodLevel() == MoodLevel.BAD) {
            System.out.println(getName() + " растерянно смотрит на Незнайку.");
        } else if (getMoodLevel() == MoodLevel.BAD && neznaika.getMoodLevel() == MoodLevel.BAD) {
            System.out.println(getName() + " тихо плачет.");
        } else if (getMoodLevel() == MoodLevel.BAD && neznaika.getMoodLevel() == MoodLevel.GOOD) {
            System.out.println(getName() + " с грустными глазами соглашается на поездку.");
        }
    }
}