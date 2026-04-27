import java.util.*;

public class Main {

    static List<Term> parse(String line) {
        if (line == null || line.trim().isEmpty()) {
            return new ArrayList<>();
        }

        line = line.replaceAll("\\s+", "");
        List<Term> terms = new ArrayList<>();
        int i = 0;
        int sign = 1;
        if (line.charAt(0) == '-') {
            sign = -1;
            i = 1;
        } else if (line.charAt(0) == '+') {
            i = 1;
        }

        StringBuilder cur = new StringBuilder();
        while (i < line.length()) {
            char c = line.charAt(i);
            if (c == '+' || c == '-') {
                if (!cur.isEmpty()) {
                    terms.add(Term.parse(cur.toString(), sign));
                    cur = new StringBuilder();
                }
                sign = c == '+' ? 1 : -1;
                i++;
                continue;
            }
            cur.append(c);
            i++;
        }
        if (!cur.isEmpty()) {
            terms.add(Term.parse(cur.toString(), sign));
        }
        return terms;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        if (!sc.hasNextLine()) {
            System.out.println("0");
            return;
        }
        String s1 = sc.nextLine();

        if (!sc.hasNextLine()) {
            System.out.println("0");
            return;
        }
        String s2 = sc.nextLine();

        List<Term> p1 = parse(s1);
        List<Term> p2 = parse(s2);

        Map<String, Term> map = new HashMap<>();
        for (int i = 0; i < p1.size(); i++) {
            Term a = p1.get(i);
            for (int j = 0; j < p2.size(); j++) {
                Term b = p2.get(j);
                Term pr = Term.multiply(a, b);
                if (pr.coeff == 0) continue;
                if (map.containsKey(pr.key())) {
                    Term old = map.get(pr.key());
                    old.coeff += pr.coeff;
                } else {
                    map.put(pr.key(), pr);
                }
            }
        }

        List<Term> res = new ArrayList<>();
        for (Term t : map.values()) {
            if (t.coeff != 0) {
                res.add(t);
            }
        }

        Collections.sort(res);

        if (res.isEmpty()) {
            System.out.println("0");
            return;
        }

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < res.size(); i++) {
            String str = res.get(i).toString();
            if (i == 0) {
                out.append(str);
            } else if (res.get(i).coeff > 0) {
                out.append(" + ").append(str);
            } else {
                out.append(" - ").append(str.substring(1));
            }
        }
        System.out.println(out);
    }
}