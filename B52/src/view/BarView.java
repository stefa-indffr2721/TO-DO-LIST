package view;

import model.BarMap;
import model.Condition;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BarView {

    private final Scanner in;
    private final PrintWriter out;

    public BarView(String inputFile, String outputFile) throws IOException {
        this.in  = new Scanner(new File(inputFile));
        this.out = new PrintWriter(outputFile);
    }

    public BarMap readMap() {
        int n = in.nextInt();

        int[] cocktails = new int[n + 1];
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        for (int i = 0; i <= n; i++) edges.add(new ArrayList<>());

        for (int i = 1; i <= n; i++) {
            cocktails[i] = in.nextInt();
            int m = in.nextInt();
            for (int j = 0; j < m; j++) {
                edges.get(i).add(in.nextInt());
            }
        }

        return new BarMap(n, cocktails, edges);
    }

    public void printResult(BarMap map, Condition condition) {
        for (int i = 1; i <= map.n; i++) {
            out.println(Condition.format(condition.onEntry[i]) + " " + Condition.format(condition.onExit[i]));
        }
        out.close();
    }
}