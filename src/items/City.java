package items;

public record City(String name, boolean isReal) {
    @Override
    public String toString() {
        return name;
    }
}