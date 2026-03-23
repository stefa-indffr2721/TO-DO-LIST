import controller.BFS;
import model.BarMap;
import model.Condition;
import view.BarView;

public class Main {
    public static void main(String[] args) throws Exception {
        BarView view = new BarView("input.txt", "output.txt");
        BFS controller = new BFS();

        BarMap map = view.readMap();
        Condition condition = controller.solve(map);
        view.printResult(map, condition);
    }
}