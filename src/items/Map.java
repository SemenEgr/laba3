package items;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private final List<City> cities;
    private final String type; // correct, joke, incorrect

    public Map(List<City> cities, String type) {
        this.cities = new ArrayList<>(cities);
        this.type = type;
    }

    public List<City> getCities() {
        return cities;
    }

    public String getType() {
        return type;
    }

    public boolean hasFlowerCity() {
        for (City city : cities) {
            if (city.name().equals("Flower City")) {
                return true;
            }
        }
        return false;
    }
}