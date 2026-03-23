package model;

import java.util.Arrays;

public class Condition {
    public static final int sober = 0;
    public static final int unknown = -1;
    public static final int do_not_visited = -1000;

    public final int[] onEntry;
    public final int[] onExit;

    public Condition(int n) {
        onEntry = new int[n + 1];
        onExit  = new int[n + 1];
        Arrays.fill(onEntry, do_not_visited);
        Arrays.fill(onExit,  do_not_visited);
    }

    public static int merge(int a, int b) {
        if (a == do_not_visited)
            return b;
        if (b == do_not_visited)
            return a;
        if (a == b)
            return a;
        return unknown;
    }

    public static int visit(int condition, int k) {
        return k > 0 ? k : condition;
    }

    public static String format(int condition) {
        if (condition == sober)   return "sober";
        if (condition == unknown) return "unknown";
        return Integer.toString(condition);
    }
}