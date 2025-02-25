package persons;

import items.Car;
import moods.MoodLevel;

import java.util.Objects;

public abstract class Character {
    private final String name;
    private int mood;

    public Character(String name, int mood) {
        if (mood < 0 || mood > 10) {
            throw new IllegalArgumentException("Mood must be between 0 and 10");
        }
        this.name = name;
        this.mood = mood;
    }

    public String getName() {
        return name;
    }

    public int getMood() {
        return mood;
    }

    public MoodLevel getMoodLevel() {
        return mood >= 5 ? MoodLevel.GOOD : MoodLevel.BAD;
    }

    public abstract void act(Character[] otherCharacters, Car car);

    public abstract void actIncorrectMap(Character[] otherCharacters, Car car);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return mood == character.mood && name.equals(character.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, mood);
    }

    @Override
    public String toString() {
        return name + " (mood: " + mood + ")";
    }
}