import java.io.*;
import java.util.*;

public class Multiply {

    static class Term {
        long coef;
        TreeMap<Character, Integer> vars = new TreeMap<>();

        Term(long c) {
            coef = c;
        }

        static Term multiply(Term a, Term b) {
            Term res = new Term(a.coef * b.coef);
            res.vars.putAll(a.vars);
            for (char v : b.vars.keySet()) {
                res.vars.put(v, res.vars.getOrDefault(v, 0) + b.vars.get(v));
            }
            return res;
        }

        String key() {
            StringBuilder sb = new StringBuilder();
            for (char c : vars.keySet()) {
                int p = vars.get(c);
                if (p > 0) {
                    sb.append(c).append("^").append(p).append(";");
                }
            }
            return sb.toString();
        }
    }

    static List<Term> parse(String s) {
        List<Term> res = new ArrayList<>();
        if (s == null || s.isEmpty()) return res;

        s = s.replace(" ", "");
        s = s.replace("--", "+");
        s = s.replace("+-", "-");
        s = s.replace("-+", "-");

        List<String> tokens = new ArrayList<>();
        int start = 0;
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '+' || c == '-') {
                tokens.add(s.substring(start, i));
                start = i;
            }
        }
        tokens.add(s.substring(start));

        for (String token : tokens) {
            if (token.isEmpty()) continue;

            long sign = 1;
            if (token.startsWith("-")) {
                sign = -1;
                token = token.substring(1);
            } else if (token.startsWith("+")) {
                token = token.substring(1);
            }
            if (token.isEmpty()) continue;

            String[] mul = token.split("\\*");

            long coef = sign;
            Term term = new Term(1);

            for (String m : mul) {
                if (m.isEmpty()) continue;

                char first = m.charAt(0);
                if (Character.isDigit(first)) {
                    try {
                        coef *= Long.parseLong(m);
                    } catch (Exception e) {
                    }
                } else if (Character.isLetter(first)) {
                    char var = first;
                    int pow = 1;
                    if (m.contains("^")) {
                        String[] pp = m.split("\\^");
                        if (pp.length > 1 && !pp[1].isEmpty()) {
                            try {
                                pow = Integer.parseInt(pp[1]);
                            } catch (Exception e) {
                                pow = 1;
                            }
                        }
                    }
                    term.vars.put(var, term.vars.getOrDefault(var, 0) + pow);
                }
            }

            term.coef = coef;
            res.add(term);
        }

        return res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String a = br.readLine();
        String b = br.readLine();

        if (a == null || b == null) {
            System.out.println("0");
            return;
        }

        List<Term> A = parse(a);
        List<Term> B = parse(b);

        Map<String, Long> map = new HashMap<>();
        Map<String, TreeMap<Character, Integer>> store = new HashMap<>();

        for (Term t1 : A) {
            for (Term t2 : B) {
                Term t = Term.multiply(t1, t2);
                if (t.coef == 0) continue;

                String key = t.key();
                map.put(key, map.getOrDefault(key, 0L) + t.coef);
                store.put(key, new TreeMap<>(t.vars));
            }
        }

        List<String> keys = new ArrayList<>(map.keySet());

        keys.sort(new Comparator<String>() {
            public int compare(String k1, String k2) {
                TreeMap<Character, Integer> v1 = store.get(k1);
                TreeMap<Character, Integer> v2 = store.get(k2);

                int sum1 = 0;
                for (int i : v1.values()) sum1 += i;
                int sum2 = 0;
                for (int i : v2.values()) sum2 += i;

                if (sum1 != sum2) return sum2 - sum1;

                Iterator<Map.Entry<Character, Integer>> it1 = v1.entrySet().iterator();
                Iterator<Map.Entry<Character, Integer>> it2 = v2.entrySet().iterator();

                while (it1.hasNext() && it2.hasNext()) {
                    Map.Entry<Character, Integer> e1 = it1.next();
                    Map.Entry<Character, Integer> e2 = it2.next();

                    if (!e1.getKey().equals(e2.getKey())) return e1.getKey() - e2.getKey();
                    if (!e1.getValue().equals(e2.getValue())) return e2.getValue() - e1.getValue();
                }

                if (it1.hasNext()) return -1;
                if (it2.hasNext()) return 1;
                return 0;
            }
        });

        StringBuilder ans = new StringBuilder();

        for (String key : keys) {
            long coef = map.get(key);
            if (coef == 0) continue;

            TreeMap<Character, Integer> vars = store.get(key);

            if (ans.length() > 0) {
                ans.append(coef > 0 ? " + " : " - ");
            } else if (coef < 0) {
                ans.append("-");
            }

            long abs = Math.abs(coef);
            boolean hasVars = !vars.isEmpty();

            if (abs != 1 || !hasVars) {
                ans.append(abs);
            }

            boolean firstVar = true;
            for (char c : vars.keySet()) {
                int p = vars.get(c);
                if (p == 0) continue;

                if (!firstVar) {
                    ans.append("*");
                } else if (abs != 1 || !hasVars) {
                    ans.append("*");
                }

                ans.append(c);
                if (p != 1) ans.append("^").append(p);

                firstVar = false;
            }
        }

        if (ans.length() == 0) {
            System.out.println("0");
            return;
        }

        System.out.println(ans.toString());
    }
}