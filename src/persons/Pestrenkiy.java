package persons;

import errors.MoodConflictException;
import items.Car;
import moods.MoodLevel;

public class Pestrenkiy extends Character {
    public Pestrenkiy(int mood) {
        super("Пестренький", mood);
    }

    @Override
    public void act(Character[] otherCharacters, Car car) throws MoodConflictException {
        Character knopochka = null;
        for (Character c : otherCharacters) {
            if (c instanceof Knopochka) {
                knopochka = c;
                break;
            }
        }

        if (knopochka == null) {
            throw new MoodConflictException("Кнопочка не найдена");
        }

        // Логика условий
        if (getMoodLevel() == MoodLevel.GOOD && knopochka.getMoodLevel() == MoodLevel.BAD) {
            System.out.println(getName() + " покорно включает грустную музыку.");
            car.setMusic("грустная");
        } else if (getMoodLevel() == MoodLevel.BAD && knopochka.getMoodLevel() == MoodLevel.BAD) {
            System.out.println(getName() + " обиженно игнорирует Кнопочку.");
        } else if (getMoodLevel() == MoodLevel.GOOD && knopochka.getMoodLevel() == MoodLevel.GOOD) {
            System.out.println(getName() + " говорит, что ему нравится эта музыка.");
        } else if (getMoodLevel() == MoodLevel.BAD && knopochka.getMoodLevel() == MoodLevel.GOOD) {
            System.out.println(getName() + " что-то бухчит под нос.");
        }
    }

    @Override
    public void actIncorrectMap(Character[] otherCharacters, Car car) throws MoodConflictException {
        Character neznaika = null;
        for (Character c : otherCharacters) {
            if (c instanceof Neznaika) {
                neznaika = c;
                break;
            }
        }

        if (neznaika == null) {
            throw new MoodConflictException("Незнайка не найден");
        }

        if (getMoodLevel() == MoodLevel.GOOD && neznaika.getMoodLevel() == MoodLevel.GOOD) {
            System.out.println(getName() + " соглашается с Незнайкой.");
        } else if (getMoodLevel() == MoodLevel.GOOD && neznaika.getMoodLevel() == MoodLevel.BAD) {
            System.out.println(getName() + " обнимает друзей.");
        } else if (getMoodLevel() == MoodLevel.BAD && neznaika.getMoodLevel() == MoodLevel.BAD) {
            System.out.println(getName() + " стоит с пустым взглядом.");
        } else if (getMoodLevel() == MoodLevel.BAD && neznaika.getMoodLevel() == MoodLevel.GOOD) {
            System.out.println(getName() + " с сарказмом говорит \"Ураа\".");
        }
    }
}