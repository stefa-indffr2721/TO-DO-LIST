import java.util.ArrayList;

public class BoxOfCats {
    private final ArrayList<Cats> box;

    public BoxOfCats() {
        this.box = new ArrayList<>();
    }

    public void addCat(Cats cat) {
        box.add(cat);
        System.out.println(cat.getName() + " добавлен в коробку");
    }

    public void removeCat(String name) {
        for (int i = 0; i < box.size(); i++) {
            if (box.get(i).getName().equals(name)) {
                box.remove(i);
                System.out.println(name + " удалён из коробки");
                return;
            }
        }
        System.out.println(name + " не найден");
    }

    public void printAllCats() {
        if (box.isEmpty()) {
            System.out.println("В коробке никого нет");
        }

        System.out.println("В коробке эти кысики:");
        for (Cats cats : box) {
            System.out.println("  " + cats);
        }
    }

    public static BoxOfCats createSeveralCats(int count) {
        BoxOfCats box = new BoxOfCats();
        for (int i = 1; i <= count; i++) {
            String name = "Барсик" + i;
            int age = i + 2;
            Cats cat = new Cats(name, age);
            box.addCat(cat);
        }
        return box;
    }

}
