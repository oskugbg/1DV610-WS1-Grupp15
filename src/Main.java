import java.util.Scanner;

public class Main {

    private final char[] consonants = new char[]{'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l',
            'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'z'};

    public Main() throws Exception {
        do {
            int direction = chooseDirection();
            String in = getInput();
            String out;
            if (direction == 1) {
                out = convertToRobberLanguage(in);
            } else {
                out = convertToNormalLanguage(in);
            }
            System.out.println(out);
        } while (repeat());
    }

    private int chooseDirection() {
        Scanner scanner = new Scanner(System.in);
        String choice;
        do {
            System.out.println("1 - Convert from normal to rob...");
            System.out.println("2 - Convert from rob... to normal");
        } while (!(choice = scanner.nextLine().trim()).matches("[12]"));
        return Integer.parseInt(choice);
    }

    private boolean repeat() {
        System.out.print("Want to try again? (Y/N) ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim().substring(0, 1).equalsIgnoreCase("Y");
    }

    private String getInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter some text: ");
        String in = scanner.nextLine().trim();
        return in;
    }

    private String convertToNormalLanguage(String in) throws Exception {
        String out = "";
        for (int i = 0; i < in.length(); i++) {
            String character = "" + in.charAt(i);
            boolean isConsonant = false;
            for (int j = 0; j < consonants.length; j++) {
                if (character.equalsIgnoreCase("" + consonants[j])) {
                    if (i > in.length() - 3)
                        throw new Exception("Felaktigt rövarspråk. Hittade konsonant för sent, strängen för kort.");
                    String character1 = "" + in.charAt(i + 1);
                    String character2 = "" + in.charAt(i + 2);
                    if (!character1.equalsIgnoreCase("O")) throw new Exception("Felaktigt rövarspråk. Saknar O.");
                    if (!character2.equalsIgnoreCase(character))
                        throw new Exception("Felaktigt rövarspråk. Saknar matchande konsonant.");
                    out += character;
                    i += 2;
                    isConsonant = true;
                }
            }
            if (!isConsonant) {
                out += character;
            }
        }
        return out;
    }

    private String convertToRobberLanguage(String in) {
        String out = "";
        for (int i = 0; i < in.length(); i++) {
            String character = "" + in.charAt(i);
            boolean isConsonant = false;
            for (int j = 0; j < consonants.length; j++) {
                if (character.equalsIgnoreCase("" + consonants[j])) {
                    if (character.equals(character.toUpperCase())) {
                        out += character + "O" + character;
                    } else {
                        out += character + "o" + character;
                    }
                    isConsonant = true;
                }
            }
            if (!isConsonant) {
                out += character;
            }
        }
        return out;
    }

    public static void main(String[] args) throws Exception {
        new Main();
    }
}
