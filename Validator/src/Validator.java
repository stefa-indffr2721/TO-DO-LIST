import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Validator {

    public static String process(String input) {

        Pattern phonePattern = Pattern.compile("^(\\+7|8)([0-9]{10})$");
        Matcher phoneMatcher = phonePattern.matcher(input);

        if (phoneMatcher.matches()) {
            String prefix = phoneMatcher.group(1);
            String digits = phoneMatcher.group(2);
            String last2 = digits.substring(8);

            if (prefix.equals("+7")) {
                return "номер зашифрован как +7********" + last2;
            } else {
                return "номер зашифрован как 8********" + last2;
            }
        }

        if (input.matches("^(\\+7|8)[0-9]+$")) {
            return "неправильно набран номер";
        }

        Pattern emailPattern = Pattern.compile("^([a-zA-Z0-9._%+-]{5,})@([a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$");
        Matcher emailMatcher = emailPattern.matcher(input);

        if (emailMatcher.matches()) {
            String localPart = emailMatcher.group(1);
            String domain = emailMatcher.group(2);

            String first2 = localPart.substring(0, 2);
            String last1 = localPart.substring(localPart.length() - 1);
            String stars = "*".repeat(localPart.length() - 3);

            return "почта зашифрована " + first2 + stars + last1 + "@" + domain;
        }

        Pattern shortPattern = Pattern.compile("^[a-zA-Z0-9._%+-]{1,4}@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        if (shortPattern.matcher(input).matches()) {
            return "ошибка, почта должна содержать больше 4 символов";
        }

        return "ошибка, данные не распознаны";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Ввод: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            if (!input.isEmpty()) {
                System.out.println("Вывод: " + process(input));
            }
        }

        scanner.close();
    }
}