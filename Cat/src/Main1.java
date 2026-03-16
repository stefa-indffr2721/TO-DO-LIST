public class Main1 {
    public static void main(String[] args) {

        System.out.println("Создаем коробку с котятами:");
        BoxOfCats box = BoxOfCats.createSeveralCats(3);
        box.printAllCats();

        box.addCat(new Cats("Муся", 5));

        box.removeCat("Барсик2");
        box.printAllCats();

    }
}