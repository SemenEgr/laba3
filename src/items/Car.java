package items;

import interactions.Interactable;
import persons.Character;

import java.util.Arrays;
import java.util.List;

public class Car implements Interactable {
    private final List items = Arrays.asList("Мягкие сиденья", "радио", "робот-водила", "Кондиционер");
    private String music = "тишина";


    public void setMusic(String music) {
        this.music = music;
    }

    public String getMusic() {
        return music;
    }

    @Override
    public void interact(Character character) {
        System.out.println("Предметы в машине:");
        for (Object item : items) {
            System.out.println(" - " + item);
        }
    }

    @Override
    public String toString() {
        return "Машина с предметами: " + items;
    }
}