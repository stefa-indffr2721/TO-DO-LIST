public class Cats {
    private final String name;
    private final int age;

    public Cats(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Кот " + name + " " + age;
    }
}