package model;

import java.util.ArrayList;

public class BarMap {
    public final int n;
    public final int[] cocktails;
    public final ArrayList<ArrayList<Integer>> edges;

    public BarMap(int n, int[] cocktails, ArrayList<ArrayList<Integer>> edges) {
        this.n = n;
        this.cocktails = cocktails;
        this.edges = edges;
    }
}