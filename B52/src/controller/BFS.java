package controller;

import model.BarMap;
import model.Condition;

import java.util.ArrayDeque;
import java.util.Queue;

public class BFS {

    public Condition solve(BarMap map) {

        Condition condition = new Condition(map.n);

        condition.onEntry[1] = Condition.sober;

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);

        while (!queue.isEmpty()) {

            int bar = queue.poll();

            int exitCondition = Condition.visit(condition.onEntry[bar], map.cocktails[bar]);

            int mergedExit = Condition.merge(condition.onExit[bar], exitCondition);

            if (mergedExit == condition.onExit[bar]) {
                continue;
            }

            condition.onExit[bar] = mergedExit;

            for (int neighbor : map.edges.get(bar)) {

                int mergedEntry = Condition.merge(condition.onEntry[neighbor], mergedExit);

                if (mergedEntry != condition.onEntry[neighbor]) {
                    condition.onEntry[neighbor] = mergedEntry;
                    queue.add(neighbor);
                }
            }
        }
        return condition;
    }
}