import java.util.*;

public class Term implements Comparable<Term> {
    int coeff;
    TreeMap<Character, Integer> vars = new TreeMap<>();

    Term(int coeff) {
        this.coeff = coeff;
    }

    int deg() {
        int sum = 0;
        for (int v : vars.values()) {
            sum += v;
        }
        return sum;
    }

    String key() {
        StringBuilder s = new StringBuilder();
        for (char c : vars.keySet()) {
            s.append(c).append(vars.get(c));
        }
        return s.toString();
    }

    static Term parse(String str, int sign) {
        if (str.isEmpty()) {
            return new Term(0);
        }

        Term t = new Term(sign);
        String[] parts = str.split("\\*");

        for (int i = 0; i < parts.length; i++) {
            String f = parts[i];

            if (f.matches("\\d+")) {
                t.coeff = t.coeff * Integer.parseInt(f);
            }
            else if (f.contains("^")) {
                char v = f.charAt(0);
                String[] split = f.split("\\^");
                int e = Integer.parseInt(split[1]);
                Integer old = t.vars.get(v);

                if (old == null) {
                    t.vars.put(v, e);
                } else {
                    t.vars.put(v, old + e);
                }
            }
            else {
                char v = f.charAt(0);
                Integer old = t.vars.get(v);

                if (old == null) {
                    t.vars.put(v, 1);
                } else {
                    t.vars.put(v, old + 1);
                }
            }
        }
        return t;
    }

    static Term multiply(Term a, Term b) {
        Term r = new Term(a.coeff * b.coeff);
        r.vars.putAll(a.vars);

        for (char c : b.vars.keySet()) {
            Integer old = r.vars.get(c);

            if (old == null) {
                r.vars.put(c, b.vars.get(c));
            } else {
                r.vars.put(c, old + b.vars.get(c));
            }
        }
        return r;
    }

    @Override
    public int compareTo(Term o) {
        if (o.deg() != deg()) {
            return o.deg() - deg();
        }

        TreeSet<Character> all = new TreeSet<>();
        all.addAll(vars.keySet());
        all.addAll(o.vars.keySet());

        for (char c : all) {
            int x = 0;
            if (vars.containsKey(c)) {
                x = vars.get(c);
            }

            int y = 0;
            if (o.vars.containsKey(c)) {
                y = o.vars.get(c);
            }

            if (x != y) {
                return y - x;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        if (coeff == 0) return "";

        String vs = "";
        for (char c : vars.keySet()) {
            int e = vars.get(c);
            if (e == 0) continue;
            if (!vs.isEmpty())
                vs += "*";
            vs += c;
            if (e > 1)
                vs += "^" + e;
        }

        int a = Math.abs(coeff);
        String sign = coeff < 0 ? "-" : "";

        if (vs.isEmpty())
            return sign + a;
        if (a == 1)
            return sign + vs;
        return sign + a + "*" + vs;
    }
}